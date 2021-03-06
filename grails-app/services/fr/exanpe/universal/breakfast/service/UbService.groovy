package fr.exanpe.universal.breakfast.service

import fr.exanpe.universal.breakfast.domain.*
import grails.transaction.Transactional
import grails.util.Holders
import org.apache.commons.lang.RandomStringUtils
import org.apache.commons.lang.StringUtils
import org.apache.commons.lang.time.FastDateFormat
import org.springframework.context.i18n.LocaleContextHolder

import java.security.MessageDigest

@Transactional
class UbService {

    def springSecurityService
    def mailService
    def grailsApplication
    def ubTemplateEngineService
    def historyService

    Team createTeam(Team team) {
        def all = Role.findAll()
        def userRole = Role.findByAuthority('ROLE_USER')
        team.save()
        TeamRole.create(team, userRole, true)
        return team
    }

    Team updateTeam(String username, Team newTeam) {
        Team current = Team.findByUsernameCI(username).get()
        current.setPassword(newTeam.password)
        current.setMail(newTeam.mail)
        current.save()
        return current
    }

    void onConnection(){
        def t = Team.get(springSecurityService.currentUser.id)
        t.lastConnection = new Date();
        t.save()
    }

    /**
     * Prepare a breakfast
     * @param date the breakfast date
     * @param suppliers the members suppliers
     * @param message an additionnal message
     * @return
     */
    def prepare(Date date, List<Member> suppliers, String message) {
        def t = Team.get(springSecurityService.currentUser.id)
        t.lastPrepare = new Date()
        t.breakfastScheduledDate = date
        t.workflowState = WorkflowState.PREPARE
        t.save()

        //reset preparing
        Member.executeUpdate("UPDATE Member m set m.preparing = false");



        Member.executeUpdate("UPDATE Member m set m.preparing = true where m in :suppliers", [suppliers:suppliers])

        def conf = t.configuration;

        if(conf.sendMail){
            def model = [:]
            model["breakfastdate"] = FastDateFormat.getInstance(Holders.applicationContext.getMessage("default.date.format", null, LocaleContextHolder.locale)).format(date)
            model["message"] = message?:""
            model["suppliers"] = suppliers.collect {it.name}.join(", ")

            suppliers.retainAll({StringUtils.isNotEmpty(it.mail)})
            def mails = suppliers.collect {it.mail}

            mailService.sendMail {
                to mails
                subject ubTemplateEngineService.merge("prepare", conf.prepareMailSubject, model)
                html ubTemplateEngineService.merge("prepare", conf.prepareMail, model)
            }
        }
    }

    /**
     * Get members according to their index in list Ordered
     * @param indexes the indexes, starting from 0
     * @return
     */
    List<Member> getMembersByIndexActive(List<Integer> indexes){
        def res = []
        def members = Member.getListOrderedActive(springSecurityService.currentUser).list(max:20)

        indexes.each {
            res << members[it]//index in the
        }

        return res;
    }

    def gather(String message, String location) {
        def t = Team.get(springSecurityService.currentUser.id)
        t.workflowState = WorkflowState.GATHER
        t.save()

        if(t.configuration.sendMail){
            def model = [:]
            model["message"] = message?:""
            model["location"] = location?:""
            model["suppliers"] = t.membersPreparing.collect {it.name}.join(", ")

            def mails = Member.findAll {
                eq "team", t
                eq "active", true
                isNotNull "mail"
                projections{
                    property("mail")
                }
            }

            def mail = t.configuration.gatheringMail
            def mailSubject = t.configuration.gatheringMailSubject

            mailService.sendMail {
                to mails
                subject ubTemplateEngineService.merge("gather", mailSubject, model)
                html ubTemplateEngineService.merge("gather", mail, model)
            }
        }
    }

    def complete(Date date, List<Member> suppliers, List<Member> attendees) {
        def t = Team.get(springSecurityService.currentUser.id)
        t.workflowState = WorkflowState.COMPLETE
        t.breakfastScheduledDate = null
        t.lastComplete = new Date()
        t.lastPrepare = null
        t.breakfastCount = t.breakfastCount + 1
        t.save()

        //reset preparing
        Member.executeUpdate("UPDATE Member m set m.preparing = false");

        //suppliers go on top
        def suppliersScale = Member.withCriteria {
            eq "team", t
            projections {
                max("scaleValue")
            }
        }[0] + 1

        //attendees get attending +1
        Member.executeUpdate("UPDATE Member m set m.attendingCount = (m.attendingCount+1) where m in :members", [members : attendees]);

        //absents or inactive get scale +1 and absent countEntries +1
        Member.executeUpdate("UPDATE Member m set m.scaleValue = (m.scaleValue+1), m.absenceCount = (m.absenceCount+1) where m not in (:members) and m.team = :team", [members : attendees, team : t]);

        //suppliers get their new scale applied (in top of the stack)
        //breakfastCount +1 and last date updated
        Member.executeUpdate("UPDATE Member m set m.scaleValue = :scale, m.lastBreakfast = :date, m.breakfastCount = (m.breakfastCount+1) where m in (:members)", [members : suppliers, date : date, scale : suppliersScale]);

        //historize
        historyService.addEntry(date, attendees.size(), suppliers.collect({it.name}) as String[])
    }


    def askForAccountConfirmation(Long teamId) {
        def team = Team.get(teamId)
        String token = getSecureToken(team.mail)

        String url = Holders.grailsApplication.config.grails.serverURL + "/register/confirm/" + team.id + "?token=" + token
        def mailSubject = Holders.applicationContext.getMessage("ub.register.account.confirmation.mail.subject", null, LocaleContextHolder.locale)

        def model = [:]
        model["teamName"] = team.teamName
        model["url"] = url

        mailService.sendMail {
            to team.mail
            subject mailSubject
            body(view: "/register/accountConfirmationMail",
                 model: model)
        }
    }

    def enableAccount(Long teamId) {
        Team team = Team.get(teamId)
        team.enabled = true
        team.save()
        return team
    }

    def isAccountTokenValid(Long teamId, String token) {
        def team = Team.get(teamId)
        if (team == null || team.enabled || team.accountLocked) {
            return false;
        }

        String tokenToMatch = getSecureToken(team.mail)
        boolean valid = (token == tokenToMatch)
        return valid;
    }

    private def getSecureToken(String teamMail) {
        MessageDigest md = MessageDigest.getInstance("MD5");
        String salt = grailsApplication.config.ub.security.salt;
        md.update(salt.getBytes());
        return md.digest(teamMail.getBytes()).toString().encodeAsBase64()
    }

    def sendContactMessage(String name, String fromContact, String message) {
        def mailSubject = Holders.applicationContext.getMessage("ub.contact.message.subject", [name] as Object[], LocaleContextHolder.locale)
        String crew = grailsApplication.config.grails.mail.username;
        def model = [:]
        model["name"] = name
        model["mail"] = fromContact
        model["message"] = message

        mailService.sendMail {
            from fromContact
            to crew
            subject mailSubject
            body(view: "/home/contactMessageMail",
                    model: model)
        }
    }

    def sendResetPassword(String mail) {
        def mailSubject = Holders.applicationContext.getMessage("ub.password.reset.mail.subject", null, LocaleContextHolder.locale)
        String newPassword = RandomStringUtils.random(8, true, true)
        Team current = Team.findByMailCI(mail).get()
        current.setPassword(newPassword)
        current.save()

        def model = [:]
        model["newPassword"] = newPassword
        model["teamName"] = current.teamName

        mailService.sendMail {
            to mail
            subject mailSubject
            body(view: "/home/resetPasswordMail",
                    model: model)
        }
    }

    def disableAccount(Long teamId) {
        Team team = Team.get(teamId)
        team.enabled = false
        team.accountLocked = true
        team.save()
        return team
    }
}

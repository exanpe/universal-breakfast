package fr.exanpe.universal.breakfast.service

import fr.exanpe.universal.breakfast.domain.*
import grails.transaction.Transactional
import grails.util.Holders
import org.apache.commons.lang.StringUtils
import org.apache.commons.lang.time.FastDateFormat
import org.springframework.context.i18n.LocaleContextHolder

@Transactional
class UbService {

    def springSecurityService
    def mailService
    def grailsApplication
    def ubTemplateEngineService

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

    def prepare(Date date, Integer[] suppliersIndexes, String message) {
        def t = Team.get(springSecurityService.currentUser.id)
        t.lastPreparation = new Date()
        t.workflowState = WorkflowState.PREPARE
        t.save()

        //reset preparing
        Member.executeUpdate("UPDATE Member m set m.preparing = false");


        def suppliers = getMembersByIndex(suppliersIndexes)
        Member.executeUpdate("UPDATE Member m set m.preparing = true where m in :suppliers", [suppliers:suppliers])

        def conf = t.configuration;

        if(conf.sendMail){
            def model = [:]
            model["breakfastdate"] = FastDateFormat.getInstance(Holders.applicationContext.getMessage("default.date.format", null, LocaleContextHolder.locale)).format(date)
            model["message"] = message?:""

            suppliers.retainAll({StringUtils.isNotEmpty(it.mail)})
            def mails = suppliers.collect {it.mail}

            mailService.sendMail {
                to mails
                subject ubTemplateEngineService.merge("prepare", conf.prepareMailSubject, model)
                html ubTemplateEngineService.merge("prepare", conf.prepareMail, model)
            }
        }
    }

    List<Member> getMembersByIndex(Integer[] indexes){
        def res = []
        def members = Member.getListOrderedActive(springSecurityService.currentUser).list(max:20)

        indexes.each {
            res << members[it]//index in the
        }

        return res;
    }

    def getTogether(String message, String location) {
        def t = Team.get(springSecurityService.currentUser.id)
        t.workflowState = WorkflowState.GATHER
        t.save()

        if(t.configuration.sendMail){
            def model = [:]
            model["message"] = message?:""
            model["location"] = location?:""

            def mails = Member.findAll {
                eq "team", t
                eq "active", true
                isNotNull "mail"
                projections{
                    property("mail")
                }
            }

            def mail = t.configuration.togetherMail
            def mailSubject = t.configuration.togetherMailSubject

            mailService.sendMail {
                to mails
                subject ubTemplateEngineService.merge("together", mailSubject, model)
                html ubTemplateEngineService.merge("together", mail, model)
            }
        }
    }
}

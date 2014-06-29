package fr.exanpe.universal.breakfast.controller.u

import fr.exanpe.universal.breakfast.domain.Team
import fr.exanpe.universal.breakfast.service.UbService
import grails.plugin.springsecurity.SpringSecurityService

class AccountController {

    UbService ubService
    SpringSecurityService springSecurityService

    def index() {

        def team = Team.get(springSecurityService.currentUser.id)
        //eager load
        def conf = team.configuration

        params.mail = springSecurityService.currentUser?.mail

        [params: params, command:flash?.command, team:team]
    }

    def update = { AccountCommand command ->
        log.debug "Account update action params: " + params
        if (command.hasErrors())
        {
            command.errors.allErrors.each {
                log.debug "error while update team: " + it
            }
            flash.command = command
            redirect(action: 'index')
            return
        }
        else {
            Team team = new Team(params)
            ubService.updateTeam(springSecurityService.currentUser?.username, team)
            flash.message = "ub.account.update.success"
            redirect(action: 'index')
            return
        }

    }

    def privacy = {
        log.debug "Account privacy action params: " + params

        def conf = Team.get(springSecurityService.currentUser.id).configuration
        conf.cardEnabled = params.cardEnabled
        conf.planningEnabled = params.planningEnabled
        conf.save(flush:true)

        flash.message = "ub.account.update.success"

        redirect(action: 'index')
    }

    def mail = {
        log.debug "Account mail action params: " + params

        def conf = Team.get(springSecurityService.currentUser.id).configuration
        conf.sendMail = params.sendMail

        conf.prepareMailSubject = params.prepareMailSubject
        conf.prepareMail = params.prepareMail

        conf.gatheringMailSubject = params.gatheringMailSubject
        conf.gatheringMail = params.gatheringMail

        conf.save(flush:true)

        flash.message = "ub.account.update.success"

        redirect(action: 'index')
    }

    def delete() {
        ubService.disableAccount(springSecurityService.currentUser.id)
        flash.message = message(code: "ub.account.delete.confirm.msg")
        redirect controller: 'home', action: 'index'
        redirect(action: 'home')
    }
}

class AccountCommand {
    String mail
    String password
    String password2

    SpringSecurityService springSecurityService

    static constraints = {
        mail blank: false, nullable: false, email: true, validator: { value, obj ->
            Team team = Team.get(obj.springSecurityService?.currentUser?.id)
            if (team.mail != value && Team.findByMailCI(value).get()) {
                return "ub.register.mail.validator.exists"
            }
        }

        password blank: false, nullable: false, minSize: 4
        password2 nullable: false, blank:false, validator: { value, obj ->
            if (obj.password != value) {
                return "ub.register.password.validator.not.match"
            }
        }
    }
}
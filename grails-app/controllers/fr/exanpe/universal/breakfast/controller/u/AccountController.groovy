package fr.exanpe.universal.breakfast.controller.u

import fr.exanpe.universal.breakfast.domain.Team
import fr.exanpe.universal.breakfast.service.UbService
import grails.plugin.springsecurity.SpringSecurityService

class AccountController {

    UbService ubService
    SpringSecurityService springSecurityService

    def index() {
        params.mail = springSecurityService.currentUser?.mail
        [params: params]
    }

    def update = { AccountCommand command ->
        log.debug "Account update action params: " + params
        if (command.hasErrors())
        {
            command.errors.allErrors.each {
                log.debug "error while update team: " + it
            }
            render(view: 'index', model: [command: command])
            return
        }
        else {
            Team team = new Team(params)
            ubService.updateTeam(springSecurityService.currentUser?.username, team)
            flash.message = "ub.account.update.success"
            render(view: 'index')
            return
        }

    }

    def delete() {
        // TODO : remove account
        render(view: 'index')
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
package fr.exanpe.universal.breakfast.controller

import fr.exanpe.universal.breakfast.domain.Team
import fr.exanpe.universal.breakfast.service.UbService
import grails.plugin.springsecurity.SpringSecurityService

class RegisterController {

    UbService ubService
    SpringSecurityService springSecurityService

    def index() {}

    def register = { RegisterCommand command ->
        log.debug "Register action params: " + params
        if (command.hasErrors())
        {
            command.errors.allErrors.each {
                log.debug "error while saving User domain via RegisterCommand :" + it
            }
            render(view: 'index', model: [command: command])
            return
        }
        else {
            Team team = ubService.createTeam(new Team(username: params.username, password: params.password, mail: params.mail, enabled : false))
            ubService.askForAccountConfirmation(team.id)
            flash.message = message(code: "ub.register.account.to.confirm", args: [team.teamName], default: '')
            redirect controller: 'home', action: 'index'
        }
    }

    def confirm(String token) {
        Long teamId = Long.valueOf(params.id)
        boolean valid = ubService.isAccountTokenValid(teamId, token);
        if (valid) {
            Team team = ubService.enableAccount(teamId)
            springSecurityService.reauthenticate(team.username, team.password)
            ubService.onConnection();
            session["help"] = true
            redirect controller: 'login', action: 'auth'
        }
        else {
            flash.message = message(code: "ub.register.account.confirm.fail")
            flash.status = 'danger'
            redirect controller: 'home', action: 'index'
        }
    }
}

class RegisterCommand {
    String username
    String mail
    String password
    String password2
    String captcha

    def simpleCaptchaService

    static constraints = {
        username blank: false, nullable: false, minSize: 4, maxSize: 32, validator: { value, obj ->
            if (Team.findByUsernameCI(value).get()) {
                return "ub.register.team.validator.exists"
            }
        }

        mail blank: false, nullable: false, email: true, validator: { value, obj ->
            if (Team.findByMailCI(value).get()) {
                return "ub.register.mail.validator.exists"
            }
        }

        password blank: false, nullable: false, minSize: 4
        password2 nullable: false, blank:false, validator: { value, obj ->
            if (obj.password != value) {
                return "ub.register.password.validator.not.match"
            }
        }

        captcha validator: { value, obj ->
            boolean captchaValid = obj.simpleCaptchaService.validateCaptcha(value)
            if (!captchaValid) {
                return "ub.register.captcha.validator.fail"
            }
        }
    }
}


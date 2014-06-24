package fr.exanpe.universal.breakfast.controller

import fr.exanpe.universal.breakfast.domain.Team
import fr.exanpe.universal.breakfast.service.UbService
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils

class HomeController {

    SpringSecurityService springSecurityService
    UbService ubService

    def index() {
        if (springSecurityService.isLoggedIn()) {
            redirect controller: 'login', action: 'auth'
            return
        }

        def config = SpringSecurityUtils.securityConfig
        String view = '/index'
        String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
        render view: view, model: [postUrl: postUrl,
                                   rememberMeParameter: config.rememberMe.parameter]
    }

    def contact() {}

    def password() {}

    def sendMessage = { ContactCommand command ->
        if (command.hasErrors())
        {
            command.errors.allErrors.each {
                log.debug "error while filling ContactCommand :" + it
            }
            render(view: 'contact', model: [command: command])
            return
        }

        ubService.sendContactMessage(command.name, command.mail, command.message)
        flash.message = message(code: "ub.contact.message.confirm")
        redirect action: 'index'
    }

    def sendResetPassword = { PasswordCommand command ->
        if (command.hasErrors())
        {
            command.errors.allErrors.each {
                log.debug "error while filling PasswordCommand :" + it
            }
            render(view: 'password', model: [command: command])
            return
        }
        ubService.sendResetPassword(command.mail)
        flash.message = message(code: "ub.password.reset.confirm")
        redirect action: 'index'
    }
}

class ContactCommand {
    String name
    String mail
    String message
    String captcha

    def simpleCaptchaService

    static constraints = {
        name blank: false, nullable: false, minSize: 4
        mail blank: false, nullable: false, email: true
        message blank: false, nullable: false, minSize: 20, maxSize: 400
        captcha validator: { value, obj ->
            boolean captchaValid = obj.simpleCaptchaService.validateCaptcha(value)
            if (!captchaValid) {
                return "ub.register.captcha.validator.fail"
            }
        }
    }
}

class PasswordCommand {
    String mail
    String captcha

    def simpleCaptchaService

    static constraints = {
        mail blank: false, nullable: false, email: true, validator: { value, obj ->
            if (!Team.findByMailCI(value).get()) {
                return "ub.password.reset.error"
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
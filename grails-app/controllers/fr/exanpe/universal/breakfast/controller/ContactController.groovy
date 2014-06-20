package fr.exanpe.universal.breakfast.controller

class ContactController {

    def grailsApplication

    def index() {
        [mail : grailsApplication.config.ub.mail,
        github : grailsApplication.config.ub.github]
    }
}

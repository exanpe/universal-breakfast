package fr.exanpe.universal.breakfast.controller

import grails.plugin.springsecurity.SpringSecurityService

class HomeController {

    SpringSecurityService springSecurityService

    def index() {
        if (springSecurityService.isLoggedIn()) {
            redirect controller: 'login', action: 'auth'
            return
        }
        render view: '/index'
    }
}

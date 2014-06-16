package fr.exanpe.universal.breakfast.controller

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.SpringSecurityUtils

class HomeController {

    SpringSecurityService springSecurityService

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

    def sendMessage() {

    }
}

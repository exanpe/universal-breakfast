package fr.exanpe.universal.breakfast.taglib

import fr.exanpe.universal.breakfast.domain.Team
import grails.plugin.springsecurity.SpringSecurityService

class UbTagLib {

    SpringSecurityService springSecurityService

    static namespace = "ub"

    static defaultEncodeAs = 'raw'
    //static encodeAsForTags = [tagName: 'raw']

    def ubTemplateEngineService

    def required = { attrs, body ->
        out << render(template: "/tpl/required",
                model: [body: body()]
            )
    }

    def teamName = { attrs ->
        def team = springSecurityService?.currentUser as Team
        out << "${team?.teamName}"
    }

    /**
     * @attr template (String) the template to display
     */
    def templateDescription = {  attrs, body ->

        def props = ubTemplateEngineService.getTemplateProps(attrs.template)

        out << render(template: "/tpl/template_description",
                model: ["props": props])
    }

    /**
     * @attr obj the Validateable object holding errors
     */
    def errors = {attrs,  body ->
        out << render(template: "/tpl/errors",
                model: ["obj": attrs.obj])
    }
}

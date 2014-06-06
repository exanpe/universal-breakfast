package fr.exanpe.universal.breakfast.service


import fr.exanpe.universal.breakfast.template.TemplateDescriptor
import fr.exanpe.universal.breakfast.template.TemplateProp

import javax.annotation.PostConstruct

/**
 * Created by jmaupoux on 04/06/14.
 */
class UbTemplateEngineService {

    static transactional = false

    def allProps = [:]

    def globalProps = []

    def allTemplates = [:]

    def grailsApplication
    def springSecurityService

    def merge(TemplatesEnum e, String mailContent, def model) {
        def props = getTemplateProps(e);

        def res = mailContent

        //executing closure with current model for all keys related to the template
        props.each { p ->
            res = res.replaceAll(p.templateKey, p.script(model))
        }

        return res;
    }

    List<TemplateProp> getTemplateProps(TemplatesEnum t) {
        def props = []

        props.addAll(globalProps)
        props.addAll(allTemplates[t].props)

        return props
    }

    @PostConstruct
    def init() {
        allProps[PropEnum.URL] = new TemplateProp(templateKey: "_URL_", script: {
            return grailsApplication.config.grails.serverURL
        })
        allProps[PropEnum.BREAKFAST_DATE] = new TemplateProp(templateKey: "_BREAKFAST_DATE_", script: { it -> return it[PropEnum.BREAKFAST_DATE] })
        allProps[PropEnum.MESSAGE] = new TemplateProp(templateKey: "_MESSAGE_", script: { it -> return it[PropEnum.MESSAGE] })
        allProps[PropEnum.LOCATION] = new TemplateProp(templateKey: "_LOCATION_", script: { it -> return it[PropEnum.LOCATION] })
        allProps[PropEnum.TEAM_NAME] = new TemplateProp(templateKey: "_TEAM_NAME_", script: { return springSecurityService.currentUser.username })

        //for all templates
        globalProps << allProps[PropEnum.URL]
        globalProps << allProps[PropEnum.TEAM_NAME]

        allTemplates[TemplatesEnum.PREPARE] = new TemplateDescriptor(props: [allProps[PropEnum.BREAKFAST_DATE], allProps[PropEnum.MESSAGE]])
    }
}

enum PropEnum {
    URL,
    BREAKFAST_DATE,
    MESSAGE,
    TEAM_NAME,
    LOCATION
}

enum TemplatesEnum {
    PREPARE,
    TOGETHER
}
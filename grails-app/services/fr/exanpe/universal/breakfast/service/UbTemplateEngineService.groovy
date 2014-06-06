package fr.exanpe.universal.breakfast.service

import fr.exanpe.universal.breakfast.template.TemplateDescriptor
import fr.exanpe.universal.breakfast.template.TemplateProp

/**
 * Created by jmaupoux on 04/06/14.
 */
class UbTemplateEngineService {

    static transactional = false

    def allProps = []

    def globalProps = []

    def templates = []

    def grailsApplication
    def springSecurityService

    def merge(String template, String mailContent, def model) {
        def props = getTemplateProps(template);

        def res = mailContent

        //executing closure with current model for all keys related to the template
        props.each { p ->
            res = res.replaceAll(p.templateKey, p.script(model))
        }

        return res;
    }

    List<TemplateProp> getTemplateProps(String template) {
        def props = []

        props.addAll(globalProps)
        props.addAll(templates.find {it.id == template}.props)

        return props
    }

    def registerProp(TemplateProp prop){
        registerProp(prop, false)
    }

    def registerProp(TemplateProp prop, boolean global){
        allProps << prop
        if(global){
            globalProps << prop
        }
    }

    def register(TemplateDescriptor desc){
        def realProps = []

        desc.props.each {propId ->
            realProps << allProps.find {p ->
                p.id == propId
            }
        }

        desc.props = realProps

        templates << desc
    }
}
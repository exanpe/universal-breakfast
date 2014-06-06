package fr.exanpe.universal.breakfast.config

import fr.exanpe.universal.breakfast.service.UbTemplateEngineService
import fr.exanpe.universal.breakfast.template.TemplateDescriptor
import fr.exanpe.universal.breakfast.template.TemplateProp
import groovy.transform.CompileStatic

/**
 * Created by jmaupoux on 06/06/14.
 */
class TemplatesConfig {

    ConfigObject config
    UbTemplateEngineService templateEngineService

    private TemplatesConfig(ConfigObject config, def templateEngineService){
        this.config = config
        this.templateEngineService = templateEngineService
    }

    @CompileStatic
    static void initialize(ConfigObject config, UbTemplateEngineService templateEngineService) {
        if (config == null) {
            return
        }

        Object o = config.get("templates")
        TemplatesConfig templatesConfig = new TemplatesConfig(config, templateEngineService)

        templatesConfig.configure((Closure<?>)o)
    }

    def props(Closure callable){
        callable.delegate = this
        callable.resolveStrategy = Closure.DELEGATE_FIRST
        callable.call()
    }

    def prop(Map map){
        templateEngineService.registerProp(new TemplateProp(map));
    }

    def propGlobal(Map map){
        templateEngineService.registerProp(new TemplateProp(map), true);
    }

    def template(Map map){
        templateEngineService.register(new TemplateDescriptor(map))
    }

    @CompileStatic
    def configure(Closure callable) {
        callable.delegate = this
        callable.resolveStrategy = Closure.DELEGATE_FIRST
        callable.call()
    }

}

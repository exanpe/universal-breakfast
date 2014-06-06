package fr.exanpe.universal.breakfast.template

import fr.exanpe.universal.breakfast.service.PropEnum
import fr.exanpe.universal.breakfast.service.TemplatesEnum
import grails.test.spock.IntegrationSpec

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class UbTemplateEngineServiceSpec extends IntegrationSpec {

    def ubTemplateEngineService

    def setup() {
    }

    def cleanup() {
    }

    void "test merge with global and model props"() {
        setup :
        def model = [:]
        model[PropEnum.BREAKFAST_DATE] = "01/01/2014"

        def res = ubTemplateEngineService.merge(TemplatesEnum.PREPARE, "hello _URL_ _BREAKFAST_DATE_", model)

        expect :

        res == "hello http://www.ub-test.com 01/01/2014"
    }
}

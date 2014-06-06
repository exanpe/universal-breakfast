package fr.exanpe.universal.breakfast.template

import grails.plugin.springsecurity.SpringSecurityService
import grails.test.spock.IntegrationSpec
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class UbTemplateEngineServiceSpec extends IntegrationSpec {

    def ubTemplateEngineService

    def setupSpec(){

        SpringSecurityService.metaClass.getCurrentUser = {
            return new User("tEaM", "", new HashSet<GrantedAuthority>())
        }

    }

    def setup() {
    }

    def cleanup() {
    }

    void "test merge with global and model props"() {
        setup :

        def model = [:]
        model["breakfastdate"] = "01/01/2014"

        def res = ubTemplateEngineService.merge("prepare", "hello _URL_ _BREAKFAST_DATE_", model)

        expect :

        res == "hello http://www.ub-test.com 01/01/2014"
    }
}

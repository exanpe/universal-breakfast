package fr.exanpe.universal.breakfast.domain

import grails.test.spock.IntegrationSpec

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class ConfigurationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test restoreDefault"() {
        setup :
        def c = new Configuration().restoreDefault()

        expect :
        c.gatheringMail != null
        c.gatheringMail.startsWith("Hi,<br/>")
        c.gatheringMailSubject.startsWith("[Universal-Breakfast]")
    }
}

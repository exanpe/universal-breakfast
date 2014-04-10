package fr.exanpe.universal.breakfast.domain

import grails.test.spock.IntegrationSpec
import spock.lang.*

/**
 *
 */
class TeamIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        setup :
        new Team(username: "uSer", password: "pass").save(flush : true, failOnError: true);

        expect :
        Team.findByUsername("UseR").list().size() == 1;
        Team.findByUsernameCase("UseR").list().size() == 1;
    }
}

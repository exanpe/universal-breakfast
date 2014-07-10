package fr.exanpe.universal.breakfast.domain

import grails.test.spock.IntegrationSpec

/**
 *
 */
class TeamIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    /**
     * username is lowercased in database
     */
    void "test de la récupération par username case-insensitive"() {
        setup :
        new Team(username: "uSer", password: "pass", mail: "test@mail.com").save(flush : true);

        expect :
        Team.findByUsername("UseR") == null;
        Team.findByUsername("user") != null;
        Team.findByUsernameCI("UseR0").get() == null ;
        Team.findByUsernameCI("UseR").get() != null;
        Team.findByUsernameCI("user").get() != null;
    }

    void "test de la récupération par mail case-insensitive"() {
        setup :
        new Team(username: "uSer", password: "pass", mail: "test@mail.com").save(flush : true);

        expect :
        Team.findByMailCI("test@mail.com").get() != null ;
        Team.findByMailCI("test0@mail.com").get() == null ;
        Team.findByMailCI("tEst@mail.com").get() != null ;
    }
}

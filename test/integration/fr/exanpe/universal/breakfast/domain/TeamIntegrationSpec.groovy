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
        setup:
        new Team(username: "uSer", password: "pass", mail: "test@mail.com").save(flush: true);

        expect:
        Team.findByUsername("UseR") == null;
        Team.findByUsername("user") != null;
        Team.findByUsernameCI("UseR0").get() == null;
        Team.findByUsernameCI("UseR").get() != null;
        Team.findByUsernameCI("user").get() != null;
    }

    void "test de la récupération par mail case-insensitive"() {
        setup:
        new Team(username: "uSer", password: "pass", mail: "test@mail.com").save(flush: true);

        expect:
        Team.findByMailCI("test@mail.com").get() != null;
        Team.findByMailCI("test0@mail.com").get() == null;
        Team.findByMailCI("tEst@mail.com").get() != null;
    }

    void "test de la récupération des Members preparing"() {
        setup:

        //info : scale and date are not synchronized
        def team = new Team(username: "uSer", password: "pass", mail: "test@mail.com");
        //order 2
        team.members << new Member(scaleValue: 1, name: "nom 2", lastBreakfast: new Date().minus(2))
        //order 1
        team.members << new Member(scaleValue: 1, name: "nom 3", lastBreakfast: new Date().minus(4))

        when:
        team.save()

        then:
        team.membersPreparing.size() == 0

        when:
        //order 4, preset as preparing
        team.members << new Member(scaleValue: 3, name: "nom 1", lastBreakfast: new Date().minus(1), preparing: true)

        then:
        team.membersPreparing.size() == 1

        when:
        //order 4, preset as preparing
        team.members << new Member(scaleValue: 3, name: "nom 1", lastBreakfast: new Date().minus(1), preparing: true)

        then:
        team.membersPreparing.size() == 2
    }
}
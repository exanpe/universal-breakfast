package fr.exanpe.universal.breakfast.domain

import grails.test.spock.IntegrationSpec

/**
 *
 */
class MemberIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test de la récupération ordonnée par team"() {
        setup :

        def mem1 = new Member(scaleValue: 3, name: "nom 1", lastBreakfast: new Date())
        def mem2 = new Member(scaleValue: 1, name: "nom 2", lastBreakfast: new Date().next())
        def mem3 = new Member(scaleValue: 2, name: "nom 3", lastBreakfast: new Date().next().next())
        def mem4 = new Member(scaleValue: 2, name: "nom 4", lastBreakfast: new Date().next())
        def mem5 = new Member(scaleValue: 2, name: "nom 5", lastBreakfast: new Date().next().next().next())
        def mem6 = new Member(scaleValue: 2, name: "nom 6", lastBreakfast: new Date().next().next().next(), active: false)

        def team = new Team(username: "uSer", password: "pass", mail: "test@mail.com");
        team.members << mem1
        team.members << mem2
        team.members << mem3
        team.members << mem4
        team.members << mem5
        team.members << mem6

        team.save(flush : true);

        expect :
        def members = Member.getListOrderedActive(team).list();
        members.size() == 5
        members[0].name == "nom 2"//lower scale
        members[1].name == "nom 4"//oldest date
        members[2].name == "nom 3"
        members[3].name == "nom 5"
        members[4].name == "nom 1"

        def allMembers = Member.getListOrdered(team).list();
        allMembers.size() == 6

        Member.countActives(team).get() == 5
    }
}

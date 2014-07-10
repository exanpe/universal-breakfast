package fr.exanpe.universal.breakfast.service

import fr.exanpe.universal.breakfast.domain.Member
import fr.exanpe.universal.breakfast.domain.Team
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.spock.IntegrationSpec
import spock.lang.Shared

/**
 *
 */
class MemberServiceIntegrationSpec extends IntegrationSpec {

    def memberService

    @Shared
    def team, team2, mem, mem2, mem3;

    def setup() {
        team = new Team(username: 'team_', password: 'te.am', mail: 'team_@universal-breakfast.com', enabled : true).save(flush: true)
        team2 = new Team(username: 'team_2', password: 'te.am', mail: 'team_2@universal-breakfast.com', enabled : true).save(flush: true)

        mem = new Member(name : "Andrew", mail: "andrew@universal-breakfast.com")
        mem2 = new Member(name : "Josh", mail: "josh@universal-breakfast.com")
        mem3 = new Member(name : "Liza", mail: "liza@universal-breakfast.com")
        mem.save(flush: true)
        mem2.save(flush: true)
        mem3.save(flush: true)

        team.addToMembers(mem)
        team2.addToMembers(mem2)
        team.addToMembers(mem3)

        team.save(flush: true)
        team2.save(flush: true)

        def springSecurityServiceMock = Mock(SpringSecurityService)
        springSecurityServiceMock.currentUser >> team
        memberService.springSecurityService = springSecurityServiceMock
    }

    def cleanup() {
    }

    def "Check if a member belongs to a team"() {
        def success
        when:
        success = memberService.checkTeam(mem)

        then:
        success == true

        when:
        success = memberService.checkTeam(mem2)

        then:
        success == false
    }

    def "Delete a member of a team"() {
        def success
        when:
        success = memberService.deleteMember(mem3)

        then:
        success == true
        team.members.size() == 1

        when:
        success = memberService.deleteMember(mem2)

        then:
        success == false
        team.members.size() == 1
    }

    def "Toggle status of a member"() {
        boolean success
        when:
        success = memberService.toggle(mem)

        then:
        success == true
        mem.active == false

        when:
        success = memberService.toggle(mem2)

        then:
        success == false
        mem2.active == true
    }

    def "Add a new member"() {
        boolean success
        Member member = new Member(name : "Milouze", mail: "milouze@universal-breakfast.com")
        int size = team.members.size()
        when:
        success = memberService.save(member)

        then:
        success == true
        team.members.size() == size + 1
    }

}

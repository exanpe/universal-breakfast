package fr.exanpe.universal.breakfast.service

import fr.exanpe.universal.breakfast.domain.Member
import fr.exanpe.universal.breakfast.domain.Team
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.spock.IntegrationSpec
import spock.lang.Shared

/**
 *
 */
class HistoryServiceIntegrationSpec extends IntegrationSpec {

    def historyService

    @Shared
    def team;

    def setup() {
        team = new Team(username: 'team', password: 'te.am', mail: 'team@universal-breakfast.com', enabled : true).save(flush: true)

        def mem = new Member(name : "Andrew", mail: "andrew@universal-breakfast.com")
        def mem2 = new Member(name : "Brian", mail: "brian@universal-breakfast.com")

        team.members << mem
        team.members << mem2

        team.save()

        def springSecurityServiceMock = Mock(SpringSecurityService)
        springSecurityServiceMock.currentUser >> team
        historyService.springSecurityService = springSecurityServiceMock
    }

    def cleanup() {
    }

    void "Test for a team without history"() {
        expect :
        historyService.count() == 0
        historyService.list().size() == 0
    }

    void "Test adding history"() {
        setup :
        historyService.addEntry(new Date().minus(3), 7, "john", "marry")

        expect :
        historyService.count() == 1

        def h = historyService.list()

        h.size() == 1
        h[0].providers == "john,marry"
        h[0].numAttendees == 7
        h[0].date == new Date().minus(3).clearTime()
        h[0].team.id == team.id
    }

    void "Test list history"() {
        setup :
        historyService.addEntry(new Date().minus(3), 7, "john", "marry")
        historyService.addEntry(new Date().plus(3), 7, "john", "marry")
        historyService.addEntry(new Date().minus(1), 7, "john", "marry")
        historyService.addEntry(new Date().plus(7), 7, "john", "marry")

        expect :
        historyService.count() == 4

        def h = historyService.list()

        h.size() == 4

        h[0].date > h[1].date && h[1].date > h[2].date && h[2].date > h[3].date
    }
}

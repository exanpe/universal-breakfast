package fr.exanpe.universal.breakfast.service

import fr.exanpe.universal.breakfast.domain.Member
import fr.exanpe.universal.breakfast.domain.Team
import fr.exanpe.universal.breakfast.domain.WorkflowState
import grails.plugin.mail.MailService
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.spock.IntegrationSpec
import grails.transaction.Transactional
import spock.lang.Shared

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@Transactional
class UbServiceSpec extends IntegrationSpec {

    def ubService
    def historyService

    @Shared
    def teamId

    def setupSpec(){
        //disabled mail sending
        MailService.metaClass.sendMail = {

        }
    }

    def setup() {
        //info : scale and date are not synchronized
        def team = new Team(username: "uSer", password: "pass", mail: "test@mail.com");
        //order 4, preset as preparing
        team.members << new Member(scaleValue: 3, name: "nom 1", lastBreakfast: new Date().minus(1), preparing: true)
        //order 2
        team.members << new Member(scaleValue: 1, name: "nom 2", lastBreakfast: new Date().minus(2))
        //order 1
        team.members << new Member(scaleValue: 1, name: "nom 3", lastBreakfast: new Date().minus(4))
        //order last
        team.members << new Member(scaleValue: 7, name: "nom 4", lastBreakfast: new Date())
        //order 1, but inactive
        team.members << new Member(scaleValue: 1, name: "nom 5", lastBreakfast: new Date().minus(9), active: false)
        //order 3
        team.members << new Member(scaleValue: 2, name: "nom 6", lastBreakfast: new Date().minus(4))

        team.save()

        def springSecurityServiceMock = Mock(SpringSecurityService)
        springSecurityServiceMock.currentUser >> team
        ubService.springSecurityService = springSecurityServiceMock
        historyService.springSecurityService = springSecurityServiceMock
    }

    def cleanup() {
    }

    void "test prepare"() {
        when :
        //index 1 is "nom 2" (actually, index 1 is the second element)
        ubService.prepare(new Date().clearTime().plus(4), ubService.getMembersByIndexActive([1]), "")

        then :
        Member.withNewSession {
            Member.findByName("nom 1").preparing == false
        }
        Member.withNewSession {
            Member.findByName("nom 2").preparing == true
        }

        Team.get(1).lastPrepare.clearTime() == new Date().clearTime()
        Team.get(1).workflowState == WorkflowState.PREPARE
    }

    void "test complete"() {
        when :
        //index 1 is "nom 2" (actually, index 1 is the second element)
        ubService.complete(new Date().minus(1),
                ubService.getMembersByIndexActive([1]),
                [1,2,3,4,5,])

        then :
        //nom 2 is supplier
        historyService.countEntries() == 1
        Member.findAllByPreparing(true).size() == 0

        Member.withNewSession {
            return Member.findByName("nom 2").scaleValue == 8;
        }
        Member.withNewSession {
            return (Member.findByName("nom 2").lastBreakfast == new Date().minus(1).clearTime());
        }
        Member.withNewSession {
            return (Member.findByName("nom 2").breakfastCount == 1);
        }

        //absent
        Member.withNewSession {
            return Member.findByName("nom 1").scaleValue == 4
        }
        Member.withNewSession {
            return Member.findByName("nom 1").scaleValue == 4
        }

        Team.get(ubService.springSecurityService.currentUser.id).lastComplete.clearTime() == new Date().clearTime()
        Team.get(ubService.springSecurityService.currentUser.id).workflowState == WorkflowState.COMPLETE
    }

    void "test complete full cycle"() {
        setup :
        def memberIdx1 = ubService.getMembersByIndexActive([0])

        when :
        for(i in 0..4)//only 5 actives
            ubService.complete(new Date().minus(4-i),
                    ubService.getMembersByIndexActive([0]),
                    ubService.getMembersByIndexActive([0,1,2,3,4,5]))


        then :
        //last is 1
        Member.getListOrderedActive(Team.get(ubService.springSecurityService.currentUser.id)).list()[0].id == memberIdx1[0].id
    }
}

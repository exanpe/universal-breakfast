package fr.exanpe.universal.breakfast.service

import fr.exanpe.universal.breakfast.domain.Member
import fr.exanpe.universal.breakfast.domain.Team
import grails.transaction.Transactional

@Transactional
class MemberService {

    def springSecurityService

    def save(Member member) {
        Member m = member.save flush:true
        def team = Team.get(springSecurityService.currentUser.id)
        team.addToMembers(m)
        team.save(flush: true)
        return true
    }

    def deleteMember(Member member) {
        if (!checkTeam(member)) return false
        Team team = Team.get(springSecurityService.currentUser.id)
        team.removeFromMembers(member)
        return true
    }

    def toggle(Member member) {
        if (!checkTeam(member)) return false
        member.active =  member.active ? false : true
        member.save flush: true
        return true
    }

    def checkTeam(Member member) {
        def team = Team.get(springSecurityService.currentUser.id)
        return (member.team == team)
    }
}

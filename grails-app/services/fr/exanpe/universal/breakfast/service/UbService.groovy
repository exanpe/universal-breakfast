package fr.exanpe.universal.breakfast.service

import fr.exanpe.universal.breakfast.domain.Role
import fr.exanpe.universal.breakfast.domain.Team
import fr.exanpe.universal.breakfast.domain.TeamRole
import grails.transaction.Transactional

@Transactional
class UbService {

    def springSecurityService

    Team createTeam(Team team) {
        def userRole = Role.findByAuthority('ROLE_USER')
        team.save()
        TeamRole.create(team, userRole, true)
        return team
    }

    Team updateTeam(String username, Team newTeam) {
        Team current = Team.findByUsername(username)
        current.setPassword(newTeam.password)
        current.setMail(newTeam.mail)
        current.save()
        return current
    }

    void onConnection(){
        def t = Team.get(springSecurityService.currentUser.id)
        t.lastConnection = new Date();
        t.save()
    }
}

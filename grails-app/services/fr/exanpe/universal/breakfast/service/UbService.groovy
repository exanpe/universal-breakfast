package fr.exanpe.universal.breakfast.service

import fr.exanpe.universal.breakfast.domain.Role
import fr.exanpe.universal.breakfast.domain.Team
import fr.exanpe.universal.breakfast.domain.TeamRole
import grails.transaction.Transactional

@Transactional
class UbService {

    Team createTeam(Team team) {
        def userRole = Role.findByAuthority('ROLE_USER')
        team.save()
        TeamRole.create(team, userRole, true)
        return team
    }
}

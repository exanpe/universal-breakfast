package fr.exanpe.universal.breakfast.service

import fr.exanpe.universal.breakfast.domain.History
import fr.exanpe.universal.breakfast.domain.Team

class HistoryService {

    static transactional = true

    def springSecurityService
    def grailsApplication

    def addEntry(Date date, Integer numAttendees, String... suppliers) {
        Team team = springSecurityService.currentUser

        History h = new History(date: date, numAttendees : numAttendees, suppliers: suppliers.join(","), team: team)
        h.save();
    }

    def list(Integer from = 0){
        Team team = springSecurityService.currentUser

        return History.findAllByTeam(team, [sort : "date", order : "desc", offset : from, max : grailsApplication.config.ub.history.perPage])
    }

    def countEntries(){
        Team team = Team.get(springSecurityService.currentUser.id)

        return History.countByTeam(team)
    }

    def clear(){
        Team team = Team.get(springSecurityService.currentUser.id)

        team.history.clear()

        team.save();
    }
}

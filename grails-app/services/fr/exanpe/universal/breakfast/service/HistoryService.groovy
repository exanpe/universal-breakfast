package fr.exanpe.universal.breakfast.service

import fr.exanpe.universal.breakfast.domain.History
import fr.exanpe.universal.breakfast.domain.Team
import fr.exanpe.universal.breakfast.util.UBConstants

class HistoryService {

    static transactional = true

    def springSecurityService

    def addEntry(Date date, Integer numAttendees, String... providers) {
        Team team = springSecurityService.currentUser

        History h = new History(date: date, numAttendees : numAttendees, providers: providers.join(","), team: team)
        h.save();
    }

    def list(Integer from = 0){
        Team team = springSecurityService.currentUser

        return History.findAllByTeam(team, [sort : "date", order : "desc", offset : from, max : UBConstants.SHOW_HISTORY_MAX])
    }

    def count(){
        Team team = springSecurityService.currentUser

        return History.countByTeam(team)
    }

    def clear(){
        Team team = Team.get(springSecurityService.currentUser.id)

        team.history = [];

        team.save();
    }
}

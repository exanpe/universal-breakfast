package fr.exanpe.universal.breakfast.controller

import fr.exanpe.universal.breakfast.domain.Member
import fr.exanpe.universal.breakfast.domain.Team

class CardController {

    def ubService
    def springSecurityService

    def member() {
        if(!params.teamName || !params.memberName){
            return redirect(controller: "home")
        }
        def team = Team.findByUsernameCI(params.teamName).get()

        if(!allowDisplay(team)){
            return redirect(controller: "home")
        }

        def member = Member.findByTeamAndNameCI(team, params.memberName).get()

        if(!member){
            return redirect(controller: "home")
        }

        [member : member, team : team]
    }

    def team() {
        if(!params.teamName){
            return redirect(controller: "home")
        }

        def team = Team.findByUsernameCI(params.teamName).get()

        if(!allowDisplay(team)){
            return redirect(controller: "home")
        }

        def count = Member.countActives(team).get()

        [team : team, memberCount : count]
    }

    private boolean allowDisplay(Team team){
        //if team and team allow or team is connected
        return team &&
                (team.configuration.cardEnabled == true ||
                        (springSecurityService.currentUser && springSecurityService.currentUser.id == team.id))
    }
}

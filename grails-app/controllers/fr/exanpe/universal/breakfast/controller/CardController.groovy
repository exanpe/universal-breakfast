package fr.exanpe.universal.breakfast.controller

import fr.exanpe.universal.breakfast.domain.Member
import fr.exanpe.universal.breakfast.domain.Team

class CardController {

    def ubService

    def member() {
        if(!params.teamName || !params.memberName){
            return redirect(controller: "home")
        }
        def team = Team.findByUsernameCI(params.teamName).get()

        if(!team){
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

        if(!team){
            return redirect(controller: "home")
        }

        def count = Member.countActives(team).get()

        [team : team, memberCount : count]
    }
}

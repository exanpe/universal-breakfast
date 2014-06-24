package fr.exanpe.universal.breakfast.controller

import fr.exanpe.universal.breakfast.domain.Member
import fr.exanpe.universal.breakfast.domain.Team

class PlanningController {

    def index() {
        if(params.teamName){
            Team team = Team.findByUsernameCI(params.teamName).get();

            if(!team.configuration.planningEnabled){
                return;
            }

            def members = Member.getListOrdered(team).list();

            [team : team, members : members]
        }
    }
}

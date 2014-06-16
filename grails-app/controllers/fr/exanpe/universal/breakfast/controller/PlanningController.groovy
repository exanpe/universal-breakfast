package fr.exanpe.universal.breakfast.controller

import fr.exanpe.universal.breakfast.domain.Member
import fr.exanpe.universal.breakfast.domain.Team

class PlanningController {

    def index() {
        //TODO UrlMapping
        if(params.teamName){

            Team team = Team.findByUsernameCI(params.teamName).get();

            def members = Member.getListOrdered(team).list();

            [team : team, members : members]
        }
    }
}

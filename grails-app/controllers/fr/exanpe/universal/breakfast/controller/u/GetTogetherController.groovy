package fr.exanpe.universal.breakfast.controller.u

import fr.exanpe.universal.breakfast.domain.Team

class GetTogetherController {

    def springSecurityService

    def ubService

    def index(){

        def team = Team.get(springSecurityService.currentUser.id)

        [team : team, configuration : team.configuration]
    }

    def gather(){
        ubService.getTogether(params?.message, params?.location)

        flash.sent = 1

        redirect(controller: "getTogether", action:"index")
    }
}

package fr.exanpe.universal.breakfast.controller.u

import fr.exanpe.universal.breakfast.domain.Member
import fr.exanpe.universal.breakfast.domain.Team

class CompleteController {

    def springSecurityService

    def index(){
        def team = Team.get(springSecurityService.currentUser.id)

        def members = Member.getListOrderedActive(team).list()

        def command = flash?.command

        if(!command){
            command = new CompleteCommand()
            //auto fill checkboxes based on prepare
            members.eachWithIndex {m, idx ->
                if(m.preparing){
                    command.suppliers.add(idx)
                    command.attendees.add(idx)
                }
            }
        }

        [team : team, members : members, command : command]
    }

    def complete(){
        //TODO
    }
}


class CompleteCommand {
    Date date
    //order Integer
    List<Integer> suppliers = []
    List<Integer> attendees = []

    def hasSuppliers(def i){
        return suppliers.find {it == i} != null
    }
    def hasAttendees(def i){
        return attendees.find {it == i} != null
    }

    static constraints = {
        date blank: false, nullable: false
        suppliers nullable: false
        attendees nullable: false
    }
}

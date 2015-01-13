package fr.exanpe.universal.breakfast.controller.u

import fr.exanpe.universal.breakfast.domain.Member
import fr.exanpe.universal.breakfast.domain.Team

class CompleteController {

    def springSecurityService

    def ubService

    def index(){
        def team = Team.get(springSecurityService.currentUser.id)

        def members = Member.getListOrderedActive(team).list()

        def command = flash?.command

        if(!command){
            command = new CompleteCommand(date : team.breakfastScheduledDate)

            //auto fill checkboxes based on prepare
            members.eachWithIndex {m, idx ->
                if(m.preparing){
                    command.suppliers.add(idx)
                }
                //default : everyone is present
                command.attendees.add(idx)
            }
        }

        [team : team, members : members, command : command]
    }

    def complete(CompleteCommand command){
        if (command.hasErrors())
        {
            command.errors.allErrors.each {
                log.debug "error while validating CompleteCommand :" + it
            }
            flash.command = command;
            redirect(action : 'index')
            return
        }

        def suppliers = ubService.getMembersByIndexActive(command.suppliers)
        def attendees = ubService.getMembersByIndexActive(command.attendees)

        ubService.complete(command.date, suppliers, attendees);

        flash.complete = 1
        redirect(action : 'index')
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

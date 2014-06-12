package fr.exanpe.universal.breakfast.controller.u

import fr.exanpe.universal.breakfast.domain.Member
import fr.exanpe.universal.breakfast.domain.Team
import grails.validation.Validateable

class PrepareController {

    def springSecurityService

    def ubService

    def index(){
        def members = Member.getListOrderedActive(springSecurityService.currentUser).list(max:20)//overflow protection

        //fresh data
        Team t = Team.get(springSecurityService.currentUser.id)

        return [members : members, command : flash?.command, team : t]
    }

    def prepare(PrepareCommand command){
        if (command.hasErrors())
        {
            command.errors.allErrors.each {
                log.debug "error while validating PrepareCommand :" + it
            }
            flash.command = command;
            redirect(action : 'index')
            return
        }

        def suppliers = ubService.getMembersByIndexActive(command.suppliers)

        //TODO JMX manage exception with mail
        ubService.prepare(command.date, suppliers, command.message);

        flash.sent = 1
        redirect(action : 'index')
    }
}

@Validateable
class PrepareCommand {
    Date date
    //order Integer
    Integer[] suppliers
    String message

    def has(def i){
        return suppliers.find {it == i} != null
    }

    static constraints = {
        date blank: false, nullable: false, min: new Date().clearTime()
        suppliers nullable: false
    }
}


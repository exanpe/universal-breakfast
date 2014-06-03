package fr.exanpe.universal.breakfast.controller.u

import fr.exanpe.universal.breakfast.domain.Member

class PrepareController {

    def springSecurityService

    def index(){
        if(flash?.params)
        params.putAll(flash.params)

        def members = Member.getListOrdered(springSecurityService.currentUser).list(max:20)//overflow protection

        return [members : members, command : flash?.command]
    }

    def prepare(PrepareCommand command){
        if (command.hasErrors())
        {
            command.errors.allErrors.each {
                log.debug "error while validating PrepareCommand :" + it
            }
            flash.params = params;
            flash.command = command;
            redirect(action : 'index')
            return
        }
        else {
            //TODO JMX
        }

    }
}


class PrepareCommand {
    Date date
    //order Integer
    Integer[] suppliers
    String message

    static constraints = {
        date blank: false, nullable: false, min: new Date().clearTime()
        suppliers nullable: false
    }
}


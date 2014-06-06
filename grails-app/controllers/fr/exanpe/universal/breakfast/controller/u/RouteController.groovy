package fr.exanpe.universal.breakfast.controller.u

import fr.exanpe.universal.breakfast.domain.Team
import fr.exanpe.universal.breakfast.domain.WorkflowState

class RouteController {

    def springSecurityService

    def index(){
        //fresh data
        Team t = Team.get(springSecurityService.currentUser.id)

        switch (t.workflowState){
            case WorkflowState.NEW :
                redirect( controller: "prepare")
                return;
            case WorkflowState.PREPARE :
                redirect( controller: "getTogether")
                return;
            case WorkflowState.GATHER :
                redirect( controller: "complete")
                return;
            case WorkflowState.COMPLETE :
                redirect( controller: "prepare")
                return;
        }

        redirect( controller: "prepare")
    }
}

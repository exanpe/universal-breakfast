package fr.exanpe.universal.breakfast.controller.u

class RouteController {

    def index(){
        //TODO algorithm : if last action was a prepare go to get together.
        // if last action was a get together go to complete.

        redirect( controller: "prepare")
    }
}

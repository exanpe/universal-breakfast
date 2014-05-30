package fr.exanpe.universal.breakfast.controller.u

import fr.exanpe.universal.breakfast.domain.History

class HistoryController {

    def historyService

    def index() {

        Integer total = historyService.count()
        List<History> list = null;

        if(total > 0){
            list = historyService.list(params.offset?params.offset.toInteger():0);
        }

        return [total : total, histories : list]
    }

    def clear() {
        historyService.clear();
        redirect(action : "index")
    }
}

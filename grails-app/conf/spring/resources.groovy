import fr.exanpe.universal.breakfast.web.UbEventListener
import fr.exanpe.universal.breakfast.web.UbSessionService

// Place your Spring DSL code here
beans = {

    ubSessionService(UbSessionService)

    ubEventListener(UbEventListener) {
        ubSessionService = ref(ubSessionService)
    }
}

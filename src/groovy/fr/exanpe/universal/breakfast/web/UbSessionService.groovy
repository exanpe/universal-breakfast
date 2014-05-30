package fr.exanpe.universal.breakfast.web

import java.util.concurrent.atomic.AtomicInteger

class UbSessionService {

    /**
     * Thead-safe session count
     */
    private static AtomicInteger count = new AtomicInteger(0)

    def incrementSessionCount() {
        count.incrementAndGet()
    }

    def decrementSessionCount() {
        count.decrementAndGet()
    }

    def getSessionCount() {
        return count.get()
    }
}

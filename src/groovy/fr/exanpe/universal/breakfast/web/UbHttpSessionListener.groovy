package fr.exanpe.universal.breakfast.web

import grails.util.Holders
import groovy.util.logging.Slf4j
import org.apache.commons.logging.LogFactory

import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

/**
 * Custom UB HttpSessionListener.
 * Used to monitor number of sessions in the current server instance.
 */
@Slf4j
class UbHttpSessionListener implements HttpSessionListener {

    UbSessionService ubSessionService

    @Override
    void sessionCreated(HttpSessionEvent se) {
        getUbSessionService().incrementSessionCount()
        log.info(">> session count on create: {}", getUbSessionService().getSessionCount())
    }

    @Override
    void sessionDestroyed(HttpSessionEvent se) {
        getUbSessionService().decrementSessionCount()
        log.info(">> session count on destroy: {}", getUbSessionService().getSessionCount())
    }

    private UbSessionService getUbSessionService() {
        if (ubSessionService == null) {
            ubSessionService = (UbSessionService) Holders.applicationContext.getBean("ubSessionService")
        }
        return ubSessionService
    }
}

package fr.exanpe.universal.breakfast.web

import fr.exanpe.universal.breakfast.exception.MaxSessionCountException
import grails.plugin.springsecurity.web.SecurityRequestHolder
import grails.util.Holders
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent

/**
 * Ub event listener used to capture authentication event.
 */
class UbEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    UbSessionService ubSessionService

    @Override
    void onApplicationEvent(AuthenticationSuccessEvent event) {
        if (ubSessionService.getSessionCount() > Holders.config.ub.session.max.count) {
            throw new MaxSessionCountException("The maximum number of sessions have been reached!")
        }
    }
}

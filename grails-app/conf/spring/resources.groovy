import fr.exanpe.universal.breakfast.i18n.ExtendedPluginAwareResourceBundleMessageSource
import fr.exanpe.universal.breakfast.security.UbUserDetailsService
import fr.exanpe.universal.breakfast.web.UbEventListener
import fr.exanpe.universal.breakfast.web.UbSessionService
import fr.exanpe.universal.breakfast.web.security.UbSuccessHandler
import grails.plugin.springsecurity.SpringSecurityUtils

// Place your Spring DSL code here
beans = {

    ubSessionService(UbSessionService)

    ubEventListener(UbEventListener) {
        ubSessionService = ref(ubSessionService)
    }

    authenticationSuccessHandler(UbSuccessHandler) {
        requestCache = ref('requestCache')
        defaultTargetUrl = SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl // '/'
        alwaysUseDefaultTargetUrl = SpringSecurityUtils.securityConfig.successHandler.alwaysUseDefault // false
        targetUrlParameter = SpringSecurityUtils.securityConfig.successHandler.targetUrlParameter // 'spring-security-redirect'
        ajaxSuccessUrl = SpringSecurityUtils.securityConfig.successHandler.ajaxSuccessUrl // '/login/ajaxSuccess'
        useReferer = SpringSecurityUtils.securityConfig.successHandler.useReferer // false
        redirectStrategy = ref('redirectStrategy')
    }

    userDetailsService(UbUserDetailsService) {
        grailsApplication = ref('grailsApplication')
    }

    messageSource(ExtendedPluginAwareResourceBundleMessageSource)  {
        basenames = "WEB-INF/grails-app/i18n/messages"
    }
}

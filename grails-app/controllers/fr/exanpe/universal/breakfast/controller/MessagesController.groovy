package fr.exanpe.universal.breakfast.controller

import org.springframework.web.servlet.support.RequestContextUtils

/**
 * Replacement for that shit jawr plugin
 * Move all messages starting with 'ub.js' to javascript "messages" global var
 *
 * Created by jmaupoux on 10/07/14.
 */
class MessagesController {
    def i18NService
    /**
    * Render dynamic JavaScript to the browser. It can be used in a GSP as:
    *
    * <script type='text/javascript' src='${createLink(controller: "javaScript", action: "scripts")}'></script>
    *
    * You can access i18n messages in javascript with window.messages variable. Example:
    *
    * alert(window.messages['my.message.key']);
    *
    */
    def scripts() {
        Locale locale = RequestContextUtils.getLocale(request)
        String messages = i18NService.messagesToJavaScript(locale)

        String content = "var messages = \$.parseJSON('$messages');"
        render contentType: 'text/javascript', text: content
    }
}

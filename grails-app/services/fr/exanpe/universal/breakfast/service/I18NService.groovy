package fr.exanpe.universal.breakfast.service

import grails.converters.JSON
import grails.plugin.cache.Cacheable

/**
 * Created by jmaupoux on 10/07/14.
 */
class I18NService {
    /** Dependency Injection for ExtendedPluginAwareResourceBundleMessageSource */
    def messageSource
    /**
     * Get's all messages for the Locale and encode to a JSON String.
     */
    @Cacheable('jsmessages')
    String messagesToJavaScript(Locale locale) {
        def messages = [:]
        Map<String,String> msgs = messageSource.listMessageCodes(locale)

        Set keys = msgs.keySet()
        for(String key : keys) {
            if(key.startsWith("ub.js."))
                messages[key] = msgs.get(key)
        }
        return ((messages as JSON).encodeAsJavaScript())
    }
}

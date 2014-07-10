package fr.exanpe.universal.breakfast.i18n

import org.codehaus.groovy.grails.context.support.PluginAwareResourceBundleMessageSource

/**
 * Created by jmaupoux on 10/07/14.
 */
class ExtendedPluginAwareResourceBundleMessageSource extends PluginAwareResourceBundleMessageSource {
    Map<String, String> listMessageCodes(Locale locale) {
        Properties properties = getMergedProperties(locale).properties
        Properties pluginProperties = getMergedPluginProperties(locale).properties
        return properties.plus(pluginProperties)
    }
}
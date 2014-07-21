package fr.exanpe.universal.breakfast.i18n

import org.codehaus.groovy.grails.context.support.PluginAwareResourceBundleMessageSource

/**
 * Created by jmaupoux on 10/07/14.
 */
class ExtendedPluginAwareResourceBundleMessageSource extends PluginAwareResourceBundleMessageSource {
    Map<String, String> listMessageCodes(Locale locale) {
        Properties merged = new Properties();
        Properties properties = getMergedProperties(locale).properties
        Properties pluginProperties = getMergedPluginProperties(locale).properties
        merged.putAll(properties)
        merged.putAll(pluginProperties)
        return merged
    }
}
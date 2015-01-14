package org.springframework.context.support

import java.util.Locale

/**
 * Extension of the Reloadable Resource Bundle Message Source that makes it possible to get the list of messages
 */
class AccessableResourceBundleMessageSource : ReloadableResourceBundleMessageSource() {
    /**
     * Get all of the defined messages for the given locale
     * @param locale the locale to get the messages for
     * @return the messages
     */
    fun getAllMessages(locale : Locale) : Map<String, String> {
        return getMergedProperties(locale).getProperties().toSortedMap() as Map<String, String>
    }
}
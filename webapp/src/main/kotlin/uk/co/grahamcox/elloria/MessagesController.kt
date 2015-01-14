package uk.co.grahamcox.elloria

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.context.support.AccessableResourceBundleMessageSource
import org.springframework.web.bind.annotation.ResponseBody
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.HashMap
import org.springframework.web.servlet.ModelAndView
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Controller to get the message bundle in a form the UI can use
 */
[Controller]
class MessagesController(private val messages: AccessableResourceBundleMessageSource) {
    /** The object mapper to use to convert the messages structure to JSON */
    val objectMapper = ObjectMapper()

    /**
     * Get the messages for the UI
     * @return the messages
     */
    [RequestMapping(value = array("/messages.js"))]
    fun getCurrentTime(): ModelAndView {
        val root : HashMap<String, Any> = hashMapOf()

        messages.getAllMessages(Locale.forLanguageTag("en-GB")).forEach { it ->
            val key = it.key
            val message = it.value
            val keyParts = key.split("\\.")
            val leaf = keyParts.last()

            keyParts.take(keyParts.size - 1).fold(root) { initial, next ->
                when (initial) {
                    is Map<String, Any> -> {
                        val newValue : HashMap<String, Any> = hashMapOf()
                        initial.putIfAbsent(next, newValue)
                        newValue
                    }
                    else -> throw IllegalStateException("Trying to add a child node to a leaf")
                }
            }.putIfAbsent(leaf, message)
        }

        val result = ModelAndView("/messages")
        result.addObject("messages", objectMapper.writeValueAsString(root))
        return result
    }
}
package uk.co.grahamcox.elloria.webapp

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import java.time.Clock
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import org.springframework.web.servlet.ModelAndView

/**
 * Controller to get the actual main webapp
 * @param clock The clock to use
 */
[Controller]
class IndexController(private val clock: Clock) {
    /**
     * Get the index page
     * @return the index page
     */
    [RequestMapping(value = array("/**"))]
    fun getIndex(): ModelAndView {
        val response = ModelAndView("/index")
        response.addObject("now", ZonedDateTime.now(clock).format(DateTimeFormatter.ISO_ZONED_DATE_TIME))
        return response
    }

}
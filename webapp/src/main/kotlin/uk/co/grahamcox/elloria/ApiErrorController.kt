package uk.co.grahamcox.elloria.webapp

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Controller to return a 404 if the user requests an unexpected API method
 */
[Controller]
[RequestMapping(value = array("/api"))]
class ApiErrorController {
    /**
     * Get the error
     * @return the error
     */
    [RequestMapping(value = array("/**"))]
    [ResponseStatus(value = HttpStatus.NOT_FOUND)]
    [ResponseBody]
    fun getIndex() = "Requested method is not supported"
}
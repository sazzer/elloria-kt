package uk.co.grahamcox.elloria.webapp

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import java.time.Clock
import org.springframework.core.io.Resource
import org.springframework.web.bind.annotation.ResponseBody
import java.util.Properties
import java.time.format.DateTimeFormatter
import java.time.ZonedDateTime

[Controller]
[RequestMapping(value = array("/api/debug"))]
class DebugController(private val clock: Clock, private val buildInfoResource: Resource) {
    /** The information about the build */
    private val buildInfo = Properties();

    {
        if (buildInfoResource.exists()) {
            buildInfo.load(buildInfoResource.getInputStream());
        }
    }


    /**
     * Get the current time according to the system
     * @return the current time
     */
    [RequestMapping(value = array("/now"))]
    [ResponseBody]
    fun getCurrentTime(): String =
            ZonedDateTime.now(clock).format(DateTimeFormatter.ISO_ZONED_DATE_TIME)

    /**
     * Get the build information
     * @return the build information
     */
    [RequestMapping(value = array("/buildInfo"))]
    [ResponseBody]
    fun getBuildInfo() = buildInfo

}
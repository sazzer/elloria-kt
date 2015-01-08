package uk.co.grahamcox.elloria.webapp

import org.springframework.context.annotation.ImportResource
import org.springframework.context.annotation.Configuration
import java.time.Clock
import java.time.ZonedDateTime
import java.time.ZoneId
import org.springframework.context.annotation.Bean

/**
 * Test context for Spring Integration tests
 */
[Configuration]
[ImportResource(array("classpath:/uk/co/grahamcox/elloria/webapp/core/config/context.xml",
        "classpath:/uk/co/grahamcox/elloria/webapp/webapp/config/context.xml"))]
open class TestContext() {
    /** The timezone for UTC */
    private val UTC_ZONE = ZoneId.of("UTC")
    /** The current time to use in all of our tests */
    open val CURRENT_TIME = ZonedDateTime.of(2015, 1, 8, 12, 40, 0, 0, UTC_ZONE)

    /**
     * Override the bean definition for the clock to be a fixed time
     */
    [Bean]
    open fun clock() = Clock.fixed(CURRENT_TIME.toInstant(), UTC_ZONE)
}
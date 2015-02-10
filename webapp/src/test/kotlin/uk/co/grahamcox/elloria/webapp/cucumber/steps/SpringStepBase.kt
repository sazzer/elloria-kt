package uk.co.grahamcox.elloria.webapp.cucumber.steps

import org.springframework.test.context.ContextConfiguration

/**
 * Base class for all steps that want to get data from the Spring context
 */
[ContextConfiguration(array("classpath:/uk/co/grahamcox/elloria/webapp/spring/context.xml"))]
open class SpringStepBase
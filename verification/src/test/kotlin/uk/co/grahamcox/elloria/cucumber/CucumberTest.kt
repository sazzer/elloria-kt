package uk.co.grahamcox.elloria.cucumber

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

/**
 * Actually run all of the Cucumber tests that are finished
 */
[RunWith(value = javaClass<Cucumber>())]
[CucumberOptions(
        format = array("pretty"),
        features = array("classpath:uk/co/grahamcox/elloria/features"),
        tags = array("~@wip")
)]
class CucumberIT
/**
 * Actually run all of the Cucumber tests that are still a work-in-progress
 */
[RunWith(value = javaClass<Cucumber>())]
[CucumberOptions(
        format = array("pretty"),
        features = array("classpath:uk/co/grahamcox/elloria/features"),
        tags = array("@wip")
)]
class CucumberWipIT
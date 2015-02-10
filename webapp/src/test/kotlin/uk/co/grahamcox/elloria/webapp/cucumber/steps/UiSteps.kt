package uk.co.grahamcox.elloria.webapp.cucumber.steps

import cucumber.api.java.en.Given
import org.springframework.beans.factory.annotation.Autowired
import uk.co.grahamcox.elloria.webapp.cucumber.WebDriverProvider
import cucumber.api.java.After
import org.slf4j.LoggerFactory

/**
 * Background steps for controlling the UI
 * @param webDriver the injected Web Driver to use
 */
class UiSteps(private val webDriver: WebDriverProvider) {
    private val log = LoggerFactory.getLogger(javaClass<UiSteps>())

    /** The HTTP URL to connect to for the tests */
    private val testUrl = System.getProperty("url.test")
    /**
     * Open up the application in the browser
     */
    [Given("^I have opened the application$")]
    fun openUi() {
        log.info("Connecting to Elloria Application on ${testUrl}")
        webDriver.webDriver.get(testUrl)
    }

    /**
     * After the test has finished, destroy the web driver
     */
    [After]
    fun closeWebDriver() {
        webDriver.destroy()
    }

}
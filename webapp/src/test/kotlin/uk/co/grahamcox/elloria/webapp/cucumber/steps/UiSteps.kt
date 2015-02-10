package uk.co.grahamcox.elloria.webapp.cucumber.steps

import cucumber.api.java.en.Given
import org.springframework.beans.factory.annotation.Autowired
import uk.co.grahamcox.elloria.webapp.cucumber.WebDriverProvider
import cucumber.api.java.After

/**
 * Background steps for controlling the UI
 * @param webDriver the injected Web Driver to use
 */
class UiSteps(private val webDriver: WebDriverProvider) {
    /**
     * Open up the application in the browser
     */
    [Given("^I have opened the application$")]
    fun openUi() {
        webDriver.webDriver.get("http://www.google.com")
    }

    /**
     * After the test has finished, destroy the web driver
     */
    [After]
    fun closeWebDriver() {
        webDriver.destroy()
    }

}
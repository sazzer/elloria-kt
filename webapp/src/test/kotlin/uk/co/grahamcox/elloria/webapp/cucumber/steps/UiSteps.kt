package uk.co.grahamcox.elloria.webapp.cucumber.steps

import cucumber.api.java.en.Given
import org.openqa.selenium.WebDriver
import org.springframework.beans.factory.annotation.Autowired

/**
 * Background steps for controlling the UI
 */
class UiSteps : SpringStepBase() {
    [Autowired]
    private var webDriver : WebDriver? = null

    /**
     * Open up the application in the browser
     */
    [Given("^I have opened the application$")]
    fun openUi() {
        webDriver?.get("http://www.google.com")
    }

}
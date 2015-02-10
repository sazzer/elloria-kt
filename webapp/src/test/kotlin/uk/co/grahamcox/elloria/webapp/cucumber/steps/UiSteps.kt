package uk.co.grahamcox.elloria.webapp.cucumber.steps

import cucumber.api.java.en.Given
import org.openqa.selenium.WebDriver
import org.springframework.beans.factory.annotation.Autowired
import uk.co.grahamcox.elloria.webapp.cucumber.SeleniumFactoryBean

/**
 * Background steps for controlling the UI
 */
class UiSteps(private val webDriver : SeleniumFactoryBean) {

    /**
     * Open up the application in the browser
     */
    [Given("^I have opened the application$")]
    fun openUi() {
        webDriver.createInstance().get("http://www.google.com")
    }

}
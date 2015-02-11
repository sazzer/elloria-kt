package uk.co.grahamcox.elloria.cucumber.pages

import uk.co.grahamcox.elloria.cucumber.WebDriverProvider
import org.openqa.selenium.By

/**
 * The Page Model to represent the main Elloria webapp
 * @param webDriverProvider the injected Web Driver to use
 */
class ElloriaPageModel(webDriverProvider: WebDriverProvider) {
    /** The actual Web Driver */
    private val webDriver = webDriverProvider.webDriver;

    /**
     * Open the Login Dialog
     */
    fun loginDialog() : LoginDialogPageModel {
        webDriver.findElement(By.className("test-login-link")).click()
        return LoginDialogPageModel(eventuallyFindElement(webDriver, By.className("test-login-dialog")))
    }
}
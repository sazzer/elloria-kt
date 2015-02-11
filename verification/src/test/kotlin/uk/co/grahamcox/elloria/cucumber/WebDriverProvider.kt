package uk.co.grahamcox.elloria.cucumber

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver
import javax.annotation.PreDestroy

/**
 * Wrapper around the Selenium WebDriver to provide lifecycle sypport fo rit
 */
class WebDriverProvider {
    /** The Web Driver to use */
    val webDriver: WebDriver = FirefoxDriver()

    /**
     * Shut down the web driver
     */
    fun destroy() {
        webDriver.close()
        webDriver.quit()
    }
}
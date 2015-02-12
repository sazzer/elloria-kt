package uk.co.grahamcox.elloria.cucumber

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver
import javax.annotation.PreDestroy
import org.slf4j.LoggerFactory

private val browserName = System.getProperty("test.browser")

/**
 * Wrapper around the Selenium WebDriver to provide lifecycle sypport fo rit
 */
class WebDriverProvider {
    /** The logger to use */
    private val log = LoggerFactory.getLogger(javaClass<WebDriverProvider>())
    /** The Web Driver to use */
    val webDriver: WebDriver

    {
        val packageName = browserName.toLowerCase()
        val className = "org.openqa.selenium.${packageName}.${browserName}Driver"
        log.info("Loading the WebDriver from class ${className}")
        webDriver = Class.forName(className).newInstance() as WebDriver
    }

    /**
     * Open the URL provided
     * @param url The URL to open
     */
    fun openUrl(url: String) {
        webDriver.get(url)
    }
    /**
     * Shut down the web driver
     */
    fun destroy() {
        webDriver.close()
        webDriver.quit()
    }
}
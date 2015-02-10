package uk.co.grahamcox.elloria.webapp.cucumber

import org.springframework.beans.factory.config.AbstractFactoryBean
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver

/**
 * Factory bean to create the WebDriver to use
 */
class SeleniumFactoryBean : AbstractFactoryBean<WebDriver>() {
    /** {@inheritDoc} */
    override fun getObjectType(): Class<out Any> {
        return javaClass<WebDriver>()
    }

    /**
     * Create an instance of the appropriate WebDriver to use for the tests
     * @return the WebDriver to use
     */
    override fun createInstance(): WebDriver {
        return FirefoxDriver()
    }
}
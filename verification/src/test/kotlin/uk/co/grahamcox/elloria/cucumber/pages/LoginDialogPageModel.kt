package uk.co.grahamcox.elloria.cucumber.pages

import org.openqa.selenium.WebElement
import org.openqa.selenium.By

/**
 * Page Model representing the Login Dialog
 * @param dialogElement The Selenium WebElement representing the dialog
 */
class LoginDialogPageModel(private val dialogElement: WebElement) {
    /**
     * Set the email to log in with
     * @param email The email to log in with
     */
    fun setEmail(email: String) {
        dialogElement.findElement(By.name("username")).whenDisplayed { input ->
            input.click()
            input.sendKeys(email)
        }
    }

    /**
     * Set the password to log in with
     * @param password The password to log in with
     */
    fun setPassword(password: String) {
        dialogElement.findElement(By.name("password")).whenDisplayed { input ->
            input.click()
            input.sendKeys(password)
        }
    }

    /**
     * Submit the login form
     */
    fun submit() {
        dialogElement.findElement(By.className("btn-primary")).whenDisplayed { button ->
            button.click()
        }
    }
}
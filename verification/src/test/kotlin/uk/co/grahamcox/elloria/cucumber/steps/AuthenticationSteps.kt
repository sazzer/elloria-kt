package uk.co.grahamcox.elloria.cucumber.steps

import cucumber.api.java.en.When
import cucumber.api.java.en.Then
import uk.co.grahamcox.elloria.cucumber.pages.ElloriaPageModel

/**
 * Cucumber steps to handle authenticating in the UI
 */
class AuthenticationSteps(private val elloria : ElloriaPageModel) {
    /**
     * Attempt to log in with the given email and password
     * @param email The email address to log in with
     * @param password The password to log in with
     */
    [When("^I try to log in as email \"(.*)\" and password \"(.*)\"$")]
    fun login(email: String, password: String) {
        val loginDialog = elloria.loginDialog()
        loginDialog.setEmail(email)
        loginDialog.setPassword(password)
    }

    /**
     * Check that we got a specific log in error
     * @param error The error message to look for
     */
    [Then("^I get the log in error \"(.*)\"$")]
    fun checkLoginError(error: String) {

    }
}
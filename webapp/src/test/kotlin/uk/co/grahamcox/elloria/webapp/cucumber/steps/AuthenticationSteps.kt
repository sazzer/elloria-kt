package uk.co.grahamcox.elloria.webapp.cucumber.steps

import cucumber.api.java.en.When
import cucumber.api.java.en.Then

/**
 * Cucumber steps to handle authenticating in the UI
 */
class AuthenticationSteps : SpringStepBase() {
    /**
     * Attempt to log in with the given email and password
     * @param email The email address to log in with
     * @param password The password to log in with
     */
    [When("^I try to log in as email \"(.*)\" and password \"(.*)\"$")]
    fun login(email: String, password: String) {

    }

    /**
     * Check that we got a specific log in error
     * @param error The error message to look for
     */
    [Then("^I get the log in error \"(.*)\"$")]
    fun checkLoginError(error: String) {

    }
}
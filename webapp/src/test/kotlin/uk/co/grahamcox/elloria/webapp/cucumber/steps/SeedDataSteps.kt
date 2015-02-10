package uk.co.grahamcox.elloria.webapp.cucumber.steps

import cucumber.api.java.en.Given

/**
 * Cucumber steps to support seed data
 */
class SeedDataSteps {
    /**
     * Ingest a user with the given username and password
     * @param email The email address of the user to ingest
     * @param password The password of the user to ingest
     */
    [Given("^a user exists with email \"(.*)\" and password \"(.*)\"$")]
    fun ingestUser(email: String, password: String) {
        print("Hello")
    }
}
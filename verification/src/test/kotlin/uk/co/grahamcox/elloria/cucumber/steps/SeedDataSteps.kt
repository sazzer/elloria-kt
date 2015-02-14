package uk.co.grahamcox.elloria.cucumber.steps

import cucumber.api.java.en.Given
import uk.co.grahamcox.elloria.api.seed.Seed
import uk.co.grahamcox.elloria.api.seed.User
import uk.co.grahamcox.elloria.cucumber.Seeder

/**
 * Cucumber steps to support seed data
 * @param seeder The seeder to use to send the seed data
 */
class SeedDataSteps(private val seeder: Seeder) {
    /**
     * Ingest a user with the given username and password
     * @param email The email address of the user to ingest
     * @param password The password of the user to ingest
     */
    [Given("^a user exists with email \"(.*)\" and password \"(.*)\"$")]
    fun ingestUser(email: String, password: String) {
        seeder.seed(Seed(users = setOf(
                User(email = email, password = password)
        )))
    }
}
package uk.co.grahamcox.elloria.api.seed

/**
 * Seed data representation of a User record
 * @param screenName The screen name to use
 * @param realName The real name to use
 * @param email The email address to use
 * @param password The password to use
 */
data class User(val screenName: String = "Not Set",
                val realName: String = "Not Set",
                val email: String,
                val password: String)

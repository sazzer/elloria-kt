package uk.co.grahamcox.elloria.api.seed

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Seed data representation of a User record
 * @param screenName The screen name to use
 * @param realName The real name to use
 * @param email The email address to use
 * @param password The password to use
 */
[JsonCreator]
data class User([JsonProperty("screenName")] val screenName: String = "Not Set",
                [JsonProperty("realName")] val realName: String = "Not Set",
                [JsonProperty("email")] val email: String,
                [JsonProperty("password")] val password: String)

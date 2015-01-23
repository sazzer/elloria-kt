package uk.co.grahamcox.elloria.user

/**
 * Representation of a users profile
 * @param screenName The screen name to use
 * @param realName The real name to use
 * @param email The email address to use
 */
data class Profile(val screenName: String,
                   val realName: String,
                   val email: String)
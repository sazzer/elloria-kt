package uk.co.grahamcox.elloria.user

/**
 * Representation of a user
 * @param profile The users profile information
 * @param credentials The collection of credentials the user can authenticate with
 */
data class User(val profile: Profile, val credentials: Set<Credentials>)
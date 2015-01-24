package uk.co.grahamcox.elloria.user

/**
 * Representation of a user
 * @param userId The ID of the user
 * @param profile The users profile information
 * @param credentials The collection of credentials the user can authenticate with
 */
data class User(val userId: UserId,
                val profile: Profile,
                val credentials: Set<Credentials>)
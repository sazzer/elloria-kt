package uk.co.grahamcox.elloria.user

import uk.co.grahamcox.elloria.user.dao.UserDao

/**
 * Base class for all exceptions thrown by the User Service
 */
open class UserException : Exception()
/**
 * Exception to indicate that a User ID was unknown
 * @param userId The User ID that was unknown
 */
class UnknownUserIdException(val userId: UserId) : UserException()
/**
 * Exception to indicate that a Users Credentials were unknown (Not Invalid)
 * The difference here is that, for example, this represents when the username is unknown
 * @param credentials The credentials that were unknown
 */
class UnknownUserCredentialsException(val credentials: Credentials) : UserException()
/**
 * Exception to indicate that a Users Credentials were Invalid.
 * The difference here is that, for example, this represents when the password is incorrect
 * @param credentials The credentials that were invalid
 */
class InvalidUserCredentialsException(val credentials: Credentials) : UserException()

/**
 * Data class to contain the pair of Type and Key for Credentials
 * @param type The type of the credentials
 * @param key The key of the credentials
 */
data class CredentialsPair(val type: String, val key: String)

/**
 * Service for managing access to user records
 */
class UserService(private val userDao: UserDao) {
    /**
     * Attempt to load the user with the given User ID
     * @param userId the ID of the user to load
     * @return the user
     */
    fun getUser(userId: UserId) : User {
        val user = userDao.getUserById(userId)
        return user ?: throw UnknownUserIdException(userId)
    }

    /**
     * Attempt to load the user with the given credentials
     * @param credentials the credentials of the user to load
     * @return the user
     */
    fun getUser(credentials: Credentials) : User {
        val credentialsPair = when (credentials) {
            is UsernameCredentials -> CredentialsPair("username", credentials.username)
            else -> throw UnknownUserCredentialsException(credentials)
        }
        val user = userDao.getUserWithCredentials(credentialsPair.type, credentialsPair.key)
        return if (user != null) {
            if (user.credentials.contains(credentials)) {
                user
            } else {
                throw InvalidUserCredentialsException(credentials)
            }
        } else {
            throw UnknownUserCredentialsException(credentials)
        }

    }
}
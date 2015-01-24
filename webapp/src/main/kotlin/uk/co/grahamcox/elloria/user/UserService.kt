package uk.co.grahamcox.elloria.user

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
 * Service for managing access to user records
 */
class UserService {
    /**
     * Attempt to load the user with the given User ID
     * @param userId the ID of the user to load
     * @return the user
     */
    fun getUser(userId: UserId) : User {
        throw UnknownUserIdException(userId)
    }

    /**
     * Attempt to load the user with the given credentials
     * @param credentials the credentials of the user to load
     * @return the user
     */
    fun getUser(credentials: Credentials) : User {
        throw UnknownUserCredentialsException(credentials)
    }
}
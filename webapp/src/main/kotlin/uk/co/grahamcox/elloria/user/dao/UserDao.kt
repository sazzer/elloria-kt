package uk.co.grahamcox.elloria.user.dao

import uk.co.grahamcox.elloria.user.UserId
import uk.co.grahamcox.elloria.user.User

/**
 * DAO to use to access User details
 */
trait UserDao {
    /**
     * Attempt to get the User with the provided User ID
     * @param userId The ID of the User to get
     * @return The User, if found, or Null if not
     */
    fun getUserById(userId : UserId) : User?

    /**
     * Attempt to get the User with the provided Credentials
     * @param credentialType The Type of Credentials to get
     * @param key The key of the Credentials - e.g. Username
     * @return The USer, if found, or Null if not
     */
    fun getUserWithCredentials(credentialType: String, key: String) : User?
}
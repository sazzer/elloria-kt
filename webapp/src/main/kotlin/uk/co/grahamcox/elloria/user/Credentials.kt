package uk.co.grahamcox.elloria.user

/**
 * Base class for the supported Credentials
 */
open class Credentials()

/**
 * Representation of a set of credentials that are a username and password combination
 * @param username The username to use
 * @param password The password to use
 */
data class UsernameCredentials(val username: String, val password: Password) : Credentials()
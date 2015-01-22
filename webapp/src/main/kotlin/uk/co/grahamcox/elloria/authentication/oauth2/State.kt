package uk.co.grahamcox.elloria.authentication.oauth2

/**
 * Representation of the State to use for a request
 * @param value The Value of the State
 */
data class State(val value: String)
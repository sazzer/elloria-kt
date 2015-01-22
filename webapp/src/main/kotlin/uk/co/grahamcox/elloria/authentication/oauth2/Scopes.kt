package uk.co.grahamcox.elloria.authentication.oauth2

/**
 * Representation of the scopes of an access token
 * @param scopes The scopes
 */
data class Scopes(val scopes: Set<String>) {

}
/**
 * Convert a string containing a space-separated set of scopes into a Scopes object
 * @param scopes The scopes to consume
 * @return the Scopes
 */
fun Scopes(scopes: String) = Scopes(setOf())
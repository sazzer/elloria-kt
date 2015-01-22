package uk.co.grahamcox.elloria.authentication.oauth2

import java.time.Clock
import java.time.ZonedDateTime

/**
 * Mechanism by which to issue an access token
 */
class TokenIssuer(private val clock: Clock) {
    /**
     * Issue a token for a Resource Owner Password Credentials token
     * @param username The username to issue for
     * @param password The password to issue for
     * @param scopes The scopes to issue for
     */
    fun issueResourceOwnerPasswordCredentialsToken(username: String, password: String, scopes: Scopes?) : IssuedToken {
        return IssuedToken(accessToken = AccessToken("abc"),
                expiry = ZonedDateTime.now(clock),
                scopes = scopes)
    }
}
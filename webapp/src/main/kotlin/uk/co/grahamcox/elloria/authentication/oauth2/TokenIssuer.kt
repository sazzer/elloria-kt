package uk.co.grahamcox.elloria.authentication.oauth2

import java.time.Clock
import java.time.ZonedDateTime
import io.jsonwebtoken.Jwts
import java.util.Date
import io.jsonwebtoken.SignatureAlgorithm

/**
 * Mechanism by which to issue an access token
 * @param clock The clock to use
 * @param serializer The serializer to use
 */
class TokenIssuer(private val clock: Clock, private val serializer: TokenSerializer) {
    /**
     * Issue a token for a Resource Owner Password Credentials token
     * @param username The username to issue for
     * @param password The password to issue for
     * @param scopes The scopes to issue for
     */
    fun issueResourceOwnerPasswordCredentialsToken(username: String, password: String, scopes: Scopes?) : IssuedToken {
        val issued = ZonedDateTime.now(clock)
        val expires = issued.plusHours(1)

        return issueToken(Token(userId = username,
                issued = issued,
                expires = expires,
                scopes = scopes))
    }

    /**
     * Actually issue a generated token
     */
    private fun issueToken(token: Token) : IssuedToken {
        val accessToken = serializer.serialize(token)
        return IssuedToken(accessToken = accessToken,
                expiry = token.expires,
                scopes = token.scopes)
    }
}
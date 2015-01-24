package uk.co.grahamcox.elloria.authentication.oauth2

import java.time.Clock
import java.time.ZonedDateTime
import uk.co.grahamcox.elloria.user.User

/**
 * Mechanism by which to issue an access token
 * @param clock The clock to use
 * @param serializer The serializer to use
 */
class TokenIssuer(private val clock: Clock, private val serializer: TokenSerializer) {
    /**
     * Issue a token to the given User
     * @param user The user to issue a token for
     * @param scopes The scopes to issue the token for
     * @return the token
     */
    fun issueToken(user: User, scopes: Scopes?) : IssuedToken {
        // For now, tokens are always issued right now to expire in an hour
        val issued = ZonedDateTime.now(clock)
        val expires = issued.plusHours(1)

        return issueToken(Token(userId = user.userId.id,
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
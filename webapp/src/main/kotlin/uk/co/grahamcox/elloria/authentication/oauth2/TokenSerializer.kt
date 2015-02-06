package uk.co.grahamcox.elloria.authentication.oauth2

import io.jsonwebtoken.Jwts
import java.util.Date
import io.jsonwebtoken.SignatureAlgorithm

/**
 * Trait describing the means to serialize tokens
 */
trait TokenSerializer {
    /**
     * Serialize the given token into a form that we can safely provide to consumers.
     * @param token The token to serialize
     * @return The serialized AccessToken value
     */
    fun serialize(token: Token) : AccessToken
}

/**
 * Mechanism to convert between an internal Token and a public AccessToken
 * @param key The key to use to sign the JWT with
 * @param issuer The value to use as the issuer of the JWT
 */
class JwtTokenSerializer(private val key: String, private val issuer: String) : TokenSerializer {
    /**
     * Serialize the given token into a JWT that we can safely provide to consumers.
     * It doesn't really matter if the consumer decodes this, because all they can get out of it is their own internal
     * User ID, which is opaque.
     * @param token The token to serialize
     * @return The serialized AccessToken value
     */
    override fun serialize(token: Token) : AccessToken {
        val issued = Date.from(token.issued.toInstant())
        val expires = Date.from(token.expires.toInstant())

        val tokenValue = Jwts.builder()
                .setIssuer(issuer)
                .setSubject(token.userId)
                .setIssuedAt(issued)
                .setExpiration(expires)
                .setNotBefore(issued)
                .claim("scope", token.scopes?.toString())
                .signWith(SignatureAlgorithm.HS256, key)
                .compact()
        return AccessToken(tokenValue)
    }
}
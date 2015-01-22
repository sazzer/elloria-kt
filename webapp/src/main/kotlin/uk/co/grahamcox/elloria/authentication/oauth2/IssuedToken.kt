package uk.co.grahamcox.elloria.authentication.oauth2

import java.time.ZonedDateTime

/**
 * Representation of an issued OAuth2 Token to be used for authentication
 * @param accessToken The actual access token
 * @param expiry When the token expires
 * @param type The type of the token
 * @param refreshToken The associated refresh token, if there is one
 */
data class IssuedToken(val accessToken: AccessToken,
                       val expiry: ZonedDateTime,
                       val type: String = "Bearer",
                       val refreshToken: RefreshToken? = null)
package uk.co.grahamcox.elloria.authentication.oauth2

import java.time.ZonedDateTime

/**
 * The internal representation of the token that was issued.
 * @param userId The internal User ID of the token
 * @param issued When the token was issued
 * @param expires When the token expires
 * @param scopes The scopes the token can support
 */
data class Token(val userId: String,
                 val issued: ZonedDateTime,
                 val expires: ZonedDateTime,
                 val scopes: Scopes?)
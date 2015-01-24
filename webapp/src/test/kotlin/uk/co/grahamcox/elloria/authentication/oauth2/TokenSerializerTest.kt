package uk.co.grahamcox.elloria.authentication.oauth2

import org.junit.Test
import java.time.ZonedDateTime
import java.time.ZoneId
import org.junit.Assert
import io.jsonwebtoken.Jwts
import java.util.Date

/**
 * Unit tests for the Token Serializer
 */
class TokenSerializerTest {
    /**
     * Test serializing a token
     */
    [Test]
    fun testSerializeToken() {
        val tokenSerializer = TokenSerializer(key = "abcdef", issuer = "Test")
        val token = Token(userId = "userId",
                issued = ZonedDateTime.of(2015, 1, 23, 23, 53, 0, 0, ZoneId.of("UTC")),
                expires = ZonedDateTime.of(2015, 4, 23, 23, 53, 0, 0, ZoneId.of("UTC")),
                scopes = Scopes(setOf("a", "b", "c")))
        val serialized = tokenSerializer.serialize(token)

        // Unfortunately you'll just have to trust me
        Assert.assertEquals("eyJhbGciOiJIUzI1NiJ9." +
                "eyJpc3MiOiJUZXN0Iiwic3ViIjoidXNlcklkIiwiaWF0IjoxNDIyMDU3MTgwLCJleHAiOjE0Mjk4MzMxODAsIm5iZiI6MTQyMjA1NzE4MCwic2NvcGUiOiJhIGIgYyJ9." +
                "Xh8mbNJVXpafbkJWgf_-OkAcjJhDjfevfViAQC6WLpQ", serialized.value)

        // We know it's a JWT, so we'll parse it and see what we see
        val jwt = Jwts.parser().setSigningKey("abcdef").parseClaimsJws(serialized.value)
        Assert.assertEquals("Test", jwt.getBody().getIssuer())
        Assert.assertEquals(token.userId, jwt.getBody().getSubject())
        Assert.assertEquals(Date.from(token.issued.toInstant()), jwt.getBody().getIssuedAt())
        Assert.assertEquals(Date.from(token.issued.toInstant()), jwt.getBody().getNotBefore())
        Assert.assertEquals(Date.from(token.expires.toInstant()), jwt.getBody().getExpiration())
        Assert.assertEquals(token.scopes.toString(), jwt.getBody().get("scope"))
    }
}
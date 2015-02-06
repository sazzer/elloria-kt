package uk.co.grahamcox.elloria.authentication.oauth2

import java.time.ZonedDateTime
import org.junit.Test
import java.time.ZoneId
import java.time.Clock
import org.easymock.EasyMock
import uk.co.grahamcox.elloria.user.User
import uk.co.grahamcox.elloria.user.UserId
import uk.co.grahamcox.elloria.user.Profile
import org.junit.Assert

/**
 * Test actually issuing a token for a user
 */
class TokenIssuerTest {
    /**
     * Issue the token
     */
    [Test]
    fun issueToken() {
        val now = ZonedDateTime.of(2015, 2, 6, 12, 48, 0, 0, ZoneId.of("UTC"))

        val user = User(userId = UserId("abc"),
                profile = Profile(screenName = "Test",
                        realName = "Test",
                        email = "Test@test.test"),
                credentials = setOf())
        val scopes = Scopes("a b c")
        val accessToken = AccessToken("def")

        val serializer = EasyMock.createMock(javaClass<TokenSerializer>())
        EasyMock.expect(serializer.serialize(Token(userId = "abc",
                issued = now,
                expires = now.plusHours(1),
                scopes = scopes)))
            .andReturn(accessToken)
        EasyMock.replay(serializer)

        val issuer = TokenIssuer(Clock.fixed(now.toInstant(), now.getZone()), serializer)

        val token = issuer.issueToken(user, scopes)

        Assert.assertEquals(accessToken, token.accessToken)
        Assert.assertEquals(now.plusHours(1), token.expiry)
        Assert.assertEquals("Bearer", token.type)
        Assert.assertEquals(scopes, token.scopes)
        Assert.assertNull(token.refreshToken)
    }
}
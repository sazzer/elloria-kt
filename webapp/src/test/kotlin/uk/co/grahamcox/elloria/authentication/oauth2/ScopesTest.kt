package uk.co.grahamcox.elloria.authentication.oauth2

import org.junit.Test
import org.junit.Assert

/**
 * Unit tests for the Scopes class
 */
class ScopesTest {
    /**
     * Test that we can successfully parse a space separated string into a set of scopes
     */
    [Test]
    fun testParseScopesString() {
        val scopes = Scopes("a b c")
        Assert.assertNotNull(scopes)
        Assert.assertEquals(setOf("a", "b", "c"), scopes.scopes)
    }
    /**
     * Test that we can successfully parse a space separated string into a set of scopes when there are more spaces
     * between the scopes
     */
    [Test]
    fun testParseScopesStringMoreSpaces() {
        val scopes = Scopes("a b   c")
        Assert.assertNotNull(scopes)
        Assert.assertEquals(setOf("a", "b", "c"), scopes.scopes)
    }
    /**
     * Test that we can successfully parse a single string into a set of scopes
     */
    [Test]
    fun testParseSingleScopeString() {
        val scopes = Scopes("abc")
        Assert.assertNotNull(scopes)
        Assert.assertEquals(setOf("abc"), scopes.scopes)
    }
    /**
     * Test that we can successfully parse the empty string into an empty Scopes object
     */
    [Test]
    fun testParseNoScopesString() {
        val scopes = Scopes("")
        Assert.assertNotNull(scopes)
        Assert.assertTrue(scopes.scopes.isEmpty())
    }

    /**
     * Test that we can successfully build a scopes string from the parsed Scopes
     */
    [Test]
    fun testBuildScopesString() {
        val scopes = Scopes(setOf("a", "b", "c"))
        Assert.assertEquals("a b c", scopes.toString())
    }

    /**
     * Test that we can successfully build a scopes string from the parsed Scopes and always get them in the correct
     * order
     */
    [Test]
    fun testBuildWrongOrderScopesString() {
        val scopes = Scopes(setOf("c", "b", "a"))
        Assert.assertEquals("a b c", scopes.toString())
    }

    /**
     * Test that we can successfully build a scopes string from the parsed Scopes and always get them in the correct
     * order
     */
    [Test]
    fun testBuildSingleScopeString() {
        val scopes = Scopes(setOf("abc"))
        Assert.assertEquals("abc", scopes.toString())
    }

    /**
     * Test that we can successfully build a scopes string from the parsed Scopes and always get them in the correct
     * order
     */
    [Test]
    fun testBuildNoScopesString() {
        val scopes = Scopes(setOf<String>())
        Assert.assertEquals("", scopes.toString())
    }
}
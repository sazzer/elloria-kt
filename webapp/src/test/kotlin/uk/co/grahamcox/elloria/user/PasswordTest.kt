package uk.co.grahamcox.elloria.user

import org.junit.Test
import org.junit.Assert

/**
 * Unit tests for the Password class
 */
class PasswordTest {
    /**
     * Test hashing the password
     */
    [Test]
    fun testHashPassword() {
        val password = Password("graham", "password")
        // Unfortunately you'll have to trust me
        Assert.assertEquals("dc3YBTbAsA3Twh2GFCP0VAR8LmsaZnpidAjqPrZyjto=", password.hashedPassword)
    }
}
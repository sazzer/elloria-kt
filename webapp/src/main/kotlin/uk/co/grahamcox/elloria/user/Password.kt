package uk.co.grahamcox.elloria.user

import java.security.MessageDigest
import java.util.Base64

/** The hash algorithm to use */
val HASH_ALGORITHM = "SHA-256"

/** The character set to get the string to hash in */
val CHARSET = Charsets.UTF_8
/**
 * Representation of a Password
 * @param hashedPassword The hashed password to use
 */
data class Password(val hashedPassword: String)


/**
 * Generate a hashed password from the provided User ID and Plain Text Password
 * The hash is generated by taking the Username and Password, combining them with a ":" separator, then computing the
 * SHA-256 hash of this and Base64 encoding the resulting bytes
 * @param username The Username to use
 * @param plainPassword The password to use
 * @return the hashed password.
 */
fun Password(username: String, plainPassword: String) : Password {
    val stringToHash = "${username}:${plainPassword}"
    val md = MessageDigest.getInstance(HASH_ALGORITHM)
    md.update(stringToHash.toByteArray(CHARSET))
    val digest = md.digest()

    return Password(String(Base64.getEncoder().encode(digest), CHARSET))
}
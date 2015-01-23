package uk.co.grahamcox.elloria.user

/**
 * Representation of a Password
 * @param hashedPassword The hashed password to use
 */
data class Password(val hashedPassword: String)

/**
 * Generate a hashed password from the provided User ID and Plain Text Password
 * @param username The Username to use
 * @param plainPassword The password to use
 * @return the hashed password. This is literally an SHA-256 hash of the string "${username}:${plainPassword}"
 */
fun Password(username: String, plainPassword: String) : Password {
    val stringToHash = "${username}:${plainPassword}"
    return Password(stringToHash)
}
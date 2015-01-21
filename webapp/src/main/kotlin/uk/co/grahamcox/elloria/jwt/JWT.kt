package uk.co.grahamcox.elloria.jwt

/**
 * Representation of the actual claims set of a JWT
 */
class JWT(val type: String = "JWT") {
    /** The actual claims that we are making */
    private val claims = hashMapOf<String, String>()

    /**
     * Get the claim identified by the provided Claims value
     * @param claim The claim to get
     * @return the claim if we have a value for it
     */
    fun claim(claim: Claims): String? = claim(claim.key)

    /**
     * Get the claim identified by the provided key
     * @param claim The claim to get
     * @return the claim if we have a value for it
     */
    fun claim(claim: String): String? = claims.get(claim)

    /**
     * Set the claim for the provided Claims value
     * @param claim The claim to set
     * @param value The new value to store
     */
    fun claim(claim: Claims, value: String) {
        claim(claim, value)
    }
    /**
     * Set the claim for the provided Claims value
     * @param claim The claim to set
     * @param value The new value to store
     */
    fun claim(claim: String, value: String) {
        claims.put(claim, value)
    }
    /**
     * Remove the claim for the provided Claims value
     * @param claim The claim to remove
     */
    fun unclaim(claim: Claims) {
        unclaim(claim)
    }
    /**
     * Remove the claim for the provided Claims value
     * @param claim The claim to remove
     */
    fun unclaim(claim: String) {
        claims.remove(claim)
    }

    /**
     * Encode the JWT into it's string form
     * @param algorithm The algorithm to use for signing the JWT
     * @return the encoded JWT
     */
    fun encode(algorithm: Algorithm = NoneAlgorithm()) : String = ""
}
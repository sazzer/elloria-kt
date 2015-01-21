package uk.co.grahamcox.elloria.jwt

/**
 * Representation of the default claims in a JSON Web Token
 */
enum class Claims(val key: String) {
    /** The issuer of the claims */
    ISSUER : Claims("iss")
    /** The subject of the claims */
    SUBJECT : Claims("sub")
    /** The intended audience of the claims */
    AUDIENCE : Claims("aud")
    /** The Expiration time of the claims */
    EXPIRATION : Claims("exp")
    /** The time before which that the claims must be ignored */
    NOT_BEFORE : Claims("nbf")
    /** The time that the claims were issued at */
    ISSUED_AT : Claims("iat")
    /** The ID of the claims */
    JWT_ID : Claims("jti")
}
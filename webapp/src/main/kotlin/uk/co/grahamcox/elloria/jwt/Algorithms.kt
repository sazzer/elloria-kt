package uk.co.grahamcox.elloria.jwt

/**
 * Base class for algorithms to use for signing JWTs
 */
open class Algorithm(val name: String)

/**
 * Algorithm to use when we aren't signing at all
 */
class NoneAlgorithm : Algorithm("none")

/**
 * Algorithm to use when we are signing with HMAC SHA-256
 */
class HS256Algorithm : Algorithm("HS256")

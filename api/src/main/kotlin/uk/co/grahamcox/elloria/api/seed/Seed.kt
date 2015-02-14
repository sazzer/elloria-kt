package uk.co.grahamcox.elloria.api.seed

/**
 * Representation of an entire set of Seed Data
 * @param users The list of users to seed
 */
data class Seed(val users: Set<User> = setOf())

package uk.co.grahamcox.elloria.jdbc

/**
 * Representation of a table for testing purposes
 */
object Users : Table() {
    val userId = column("user_id", javaClass<Int>())
    val screenName = column("screen_name", javaClass<String>())
    val realName = column("real_name", javaClass<String>())
    val email = column("email", javaClass<String>())
}
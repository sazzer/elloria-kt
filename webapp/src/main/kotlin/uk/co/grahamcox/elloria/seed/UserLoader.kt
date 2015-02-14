package uk.co.grahamcox.elloria.seed

import uk.co.grahamcox.elloria.api.seed.Seed
import uk.co.grahamcox.elloria.api.seed.User
import com.mongodb.client.MongoDatabase
import org.bson.Document
import uk.co.grahamcox.elloria.user.Password

/**
 * Load user details
 */
class UserLoader(private val database: MongoDatabase) : SeedDataLoader {
    /** The MongoDB Collection to work with */
    private val userCollection = database.getCollection("users")
    /**
     * Load all of the users
     * @param seed The seed data to use
     */
    override fun load(seed: Seed) {
        seed.users.forEach { user -> load(user) }
    }

    /**
     * Load the particular user
     * @param user The user to load
     */
    private fun load(user: User) {
        userCollection.insertOne(Document(mapOf(
                "screenName" to user.screenName,
                "realName" to user.realName,
                "email" to user.email,
                "credentials" to listOf(
                        mapOf(
                                "type" to "username",
                                "key" to user.email,
                                "password" to Password(user.email, user.password).hashedPassword
                        )
                )
        )))
    }
}
package uk.co.grahamcox.elloria.user.dao

import uk.co.grahamcox.elloria.user.UserId
import uk.co.grahamcox.elloria.user.User
import com.mongodb.client.MongoDatabase
import uk.co.grahamcox.elloria.mongodb.MongoDao
import org.bson.types.ObjectId
import org.bson.Document
import uk.co.grahamcox.elloria.user.Profile
import uk.co.grahamcox.elloria.user.UsernameCredentials
import uk.co.grahamcox.elloria.user.Password

/**
 * MongoDB implementation of the User DAO
 */
class MongoUserDao(database: MongoDatabase) : UserDao, MongoDao<ObjectId, User>(database, "users") {
    /** @inheritDoc */
    override fun getUserById(userId: UserId): User? = getById(ObjectId(userId.id))

    /** @inheritDoc */
    override fun getUserWithCredentials(credentialType: String, key: String): User? {
        val queryDocument = Document(mapOf("credentials" to
                mapOf("\$elemMatch" to
                        mapOf("type" to credentialType, "key" to key)
                )
        ))
        return getFirstByQuery(queryDocument)
    }

    /** @inheritDoc */
    override fun parseDocument(doc: Document): User? {
        return try {
            val userId = UserId((doc.getObjectId("_id")).toString())
            val profile = Profile(
                    screenName = doc.getString("screenName"),
                    realName = doc.getString("realName"),
                    email = doc.getString("email")
            )
            val credentials = ((doc.get("credentials") as List<Document>?) ?: listOf<Document>()).map { c ->
                val type = c.getString("type")
                val key = c.getString("key")
                when (type) {
                    "username" -> UsernameCredentials(key, Password(c.getString("password")))
                    else -> null
                }
            }.filterNotNull().toSet()

            User(userId = userId,
                    profile = profile,
                    credentials = credentials)
        } catch (e: IllegalStateException) {
            null
        }
    }
}
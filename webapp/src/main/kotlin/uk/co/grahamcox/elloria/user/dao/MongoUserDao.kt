package uk.co.grahamcox.elloria.user.dao

import uk.co.grahamcox.elloria.user.UserId
import uk.co.grahamcox.elloria.user.User
import com.mongodb.client.MongoDatabase
import uk.co.grahamcox.elloria.mongodb.MongoDao
import org.bson.types.ObjectId
import org.bson.Document

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
    override fun parseDocument(doc: Document): User {
        throw UnsupportedOperationException()
    }
}
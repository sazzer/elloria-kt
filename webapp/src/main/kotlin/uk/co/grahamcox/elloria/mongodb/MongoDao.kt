package uk.co.grahamcox.elloria.mongodb

import com.mongodb.client.MongoDatabase
import com.mongodb.client.MongoCollection
import org.bson.Document

/**
 * Base class for MongoDB DAOs
 */
abstract class MongoDao<ID: Any, TYPE: Any>(private val database: MongoDatabase, private val collectionName: String) {
    /** The MongoDB Collection to use */
    protected val collection : MongoCollection<Document> = database.getCollection(collectionName)

    /**
     * Get the model from this collection that has the appropriate ID
     * @param id The ID to use
     * @return the translated model object, or null if there wasn't one
     */
    protected fun getById(id: ID) : TYPE? = getFirstByQuery(Document("_id", id))

    /**
     * Get the first model that matches the given query. Hopefully only a single docment will match
     * @param query The query to use
     * @return the translated model object, or null if there wasn't one
     */
    protected fun getFirstByQuery(query: Document) : TYPE? {
        val doc = collection.find(query).first()
        val result = if (doc != null) {
            parseDocument(doc)
        } else {
            null
        }
        return result
    }
    /**
     * Parse the document that we've just loaded from the database to the return type
     * @param doc The document to parse
     * @return the model object
     */
    protected abstract fun parseDocument(doc: Document) : TYPE
}
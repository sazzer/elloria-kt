package uk.co.grahamcox.elloria.mongodb

import com.mongodb.client.MongoDatabase
import org.slf4j.LoggerFactory
import org.springframework.context.ResourceLoaderAware
import org.springframework.core.io.ResourceLoader
import org.springframework.core.io.DefaultResourceLoader
import java.io.FileNotFoundException
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.regex.Pattern
import org.bson.types.ObjectId
import uk.co.grahamcox.elloria.user.Password
import org.bson.Document

/** The Regex pattern to discover if a string is to be processed */
val PROCESS_STRING_PATTERN = Pattern.compile("^::(.+)::(.*)$")

/** Map of the processors to use, as a type to a function to process the value */
val PROCESSORS = mapOf<String, (String) -> Any>(
        "objectid" to { input -> ObjectId(input)},
        "password" to { input ->
            val (username, password) = input.split("/")
            Password(username, password).hashedPassword
        }
)
/**
 * Process a single value
 * @param value The value to process
 * @return the processed value
 */
fun processValue(value: Any?): Any? = when (value) {
    is List<*> -> process(value)
    is Map<*, *> -> process(value)
    is String -> processString(value)
    else -> value
}

/**
 * Process a string to convert it if necessary to something else.
 * Any string that starts with a prefix of "::<type>::" will get converted
 * @param value The string to convert
 * @return the converted value
 */
fun processString(input: String) : Any {
    val matcher = PROCESS_STRING_PATTERN.matcher(input)
    return if (matcher.find()) {
        val type = matcher.group(1)
        val value = matcher.group(2)
        val processor = PROCESSORS.get(type)
        if (processor != null) {
            processor(value)
        } else {
            input
        }
    } else {
        input
    }
}

/**
 * Process the provided list of data
 * @param data The list to process
 * @return the processed list
 */
fun process(data: List<*>) : List<*> = data.map { value -> processValue(value)}

/**
 * Process the provided map of data
 * @param data The map to process
 * @return the processed map
 */
fun process(data: Map<*, *>) : Map<*, *> = data.mapValues { e -> processValue(e.value) }

/**
 * Mechanism to set up seed data for the system when it first starts
 * @param database The Mongo Database to seed
 */
class SeedDataProcessor(private val database: MongoDatabase) : ResourceLoaderAware {
    /** The logger to use */
    private val log = LoggerFactory.getLogger(javaClass<SeedDataProcessor>())
    /** The Jackson Object Mapper to use */
    private val objectMapper = ObjectMapper()
    /** The resource loader to use */
    private var resourceLoader : ResourceLoader = DefaultResourceLoader()

    /** {@inheritdoc} */
    override fun setResourceLoader(resourceLoader: ResourceLoader) {
        this.resourceLoader = resourceLoader
    }

    /**
     * Seed all of the collections specified
     * @param data Map of collection name to the resource containing the seed data
     */
    fun seed(data: Map<String, String>) {
        data.forEach { e -> seed(e.key, e.value) }
    }

    /**
     * Seed a single collection with the data specified
     * @param collection The collection to seed into
     * @param source The source data to seed from
     */
    fun seed(collection: String, source: String) {
        log.debug("Seeding collection {} with data from {}", collection, source)
        val resource = resourceLoader.getResource(source)
        if (resource.exists()) {
            val inputStream = resource.getInputStream()
            val records = objectMapper.readValue(inputStream, javaClass<List<*>>())
            log.debug("Records: {}", records)
            val processed = process(records)
            log.debug("Processed Records: {}", processed)

            database.getCollection(collection).insertMany(processed.map { record ->
                when (record) {
                    is Map<*, *> -> Document(record as Map<String, Any>)
                    else -> null
                }
            }.filterNotNull())

        } else {
            throw FileNotFoundException(source)
        }
    }
}
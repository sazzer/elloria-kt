package uk.co.grahamcox.elloria.cucumber

import uk.co.grahamcox.elloria.api.seed.Seed
import org.slf4j.LoggerFactory

/**
 * Mechanism for sending seed data to the server
 */
class Seeder {
    /** The logger to use */
    private val log = LoggerFactory.getLogger(javaClass<Seeder>())
    /** The URL to send the seed data too */
    private val seedUrl: String

    {
        val testUrl = System.getProperty("test.url")
        seedUrl = "${testUrl}/api/seed"
    }
    /**
     * Actually send the seed data
     * @param data The seed data to send
     */
    fun seed(data: Seed) {
        log.debug("Sending seed data {} to {}", data, seedUrl)
    }
}
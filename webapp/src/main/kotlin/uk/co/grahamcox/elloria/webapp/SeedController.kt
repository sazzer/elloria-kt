package uk.co.grahamcox.elloria.webapp

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestBody
import uk.co.grahamcox.elloria.api.seed.Seed
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

/**
 * Class to support ingesting of seed data for tests
 */
[Controller]
[RequestMapping(value = array("/api/seed"))]
class SeedController {
    /** The logger to use */
    private val log = LoggerFactory.getLogger(javaClass<SeedController>())
    /**
     * Actually consume some seed data
     */
    [RequestMapping(method = array(RequestMethod.PUT))]
    fun seed([RequestBody] seed: Seed): ResponseEntity<String> {
        log.info("Ingesting seed data {}", seed)
        return ResponseEntity<String>(HttpStatus.CREATED)
    }
}
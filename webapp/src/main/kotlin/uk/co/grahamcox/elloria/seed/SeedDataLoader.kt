package uk.co.grahamcox.elloria.seed

import uk.co.grahamcox.elloria.api.seed.Seed

/**
 * Mechanism by which we can load seed data into the system
 */
trait SeedDataLoader {
    /**
     * Actually load the seed data
     * @param seed The seed data to load
     */
    fun load(seed: Seed)
}
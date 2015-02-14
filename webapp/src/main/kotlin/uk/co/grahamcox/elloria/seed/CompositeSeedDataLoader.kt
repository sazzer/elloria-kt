package uk.co.grahamcox.elloria.seed

import uk.co.grahamcox.elloria.api.seed.Seed

/**
 * Seed Data Loader that works through a list of delegates
 */
class CompositeSeedDataLoader(private val loaders: List<SeedDataLoader>) : SeedDataLoader {
    /** {@inheritDoc} */
    override fun load(seed: Seed) {
        loaders.forEach { loader -> loader.load(seed) }
    }
}
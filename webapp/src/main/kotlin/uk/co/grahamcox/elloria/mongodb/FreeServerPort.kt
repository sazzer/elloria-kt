package uk.co.grahamcox.elloria.mongodb

import org.springframework.beans.factory.config.AbstractFactoryBean
import de.flapdoodle.embed.process.runtime.Network

/**
 * Spring Factory Bean to generate a free server port to use for the MongoDB server to run on
 */
class FreeServerPort : AbstractFactoryBean<Int>() {
    /** @inheritDoc */
    override fun getObjectType(): Class<out Any> = javaClass<Int>()

    /** @inheritDoc */
    override fun createInstance(): Int = Network.getFreeServerPort()
}
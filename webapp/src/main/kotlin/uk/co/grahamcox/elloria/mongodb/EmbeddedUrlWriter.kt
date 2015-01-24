package uk.co.grahamcox.elloria.mongodb

import org.springframework.beans.factory.config.AbstractFactoryBean

/**
 * Spring Factory Bean to generate a URL to the embedded MongoDB server
 */
class EmbeddedUrlWriter(private val port: Int) : AbstractFactoryBean<String>() {
    /** @inheritDoc */
    override fun getObjectType(): Class<out Any> = javaClass<String>()

    /** @inheritDoc */
    override fun createInstance(): String = "mongodb://localhost:${port}"
}
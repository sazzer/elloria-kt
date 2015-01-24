package uk.co.grahamcox.elloria.mongodb

import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.runtime.Network
import de.flapdoodle.embed.mongo.config.Net

/**
 * Build an Embedded Mongo DB system
 * @param port The port to listen on
 * @param version The server version to start
 */
class EmbeddedMongoDb(private val port: Int = 12345, private val version : Version.Main = Version.Main.PRODUCTION) {
    /** The MongoD Starter to use */
    private val starter = MongodStarter.getDefaultInstance()
    /** The MongoD configuration to use */
    private val mongodConfig = MongodConfigBuilder()
        .version(version)
        .net(Net(port, Network.localhostIsIPv6()))
        .build()
    /** The MongoD Executable to use */
    private val mongodExecutable = starter.prepare(mongodConfig)

    /**
     * Start the server
     */
    fun start() {
        mongodExecutable.start()
    }

    /**
     * Stop the server
     */
    fun stop() {
        mongodExecutable.stop()
    }
}
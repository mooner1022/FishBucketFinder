package dev.mooner

import io.ktor.application.*
import dev.mooner.plugins.*
import io.ktor.locations.*
import java.io.File

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

val bucketSearcher = BucketSearcher(File(Application::class.java.protectionDomain.codeSource.location.toURI()).path)

@KtorExperimentalLocationsAPI
@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureTemplating()
    configureSerialization()
}

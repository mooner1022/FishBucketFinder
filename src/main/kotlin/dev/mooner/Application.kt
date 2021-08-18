package dev.mooner

import io.ktor.application.*
import dev.mooner.plugins.*
import io.ktor.locations.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

val PARENT_DIRECTORY: String = File("").absolutePath

val bucketSearcher = BucketSearcher(File(PARENT_DIRECTORY, "chats.txt").path).apply {
    preProcess {
        println("preProcess() done, size: ${it.size}")
        println("flushing file...")
        val encoded = Json.encodeToString(it)
        File(PARENT_DIRECTORY, "preprocessed.json").writeText(encoded, Charsets.UTF_8)
        println("flush success")
    }
}

@KtorExperimentalLocationsAPI
@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureSerialization()
}

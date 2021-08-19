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

const val loadFromChat = false
val bucketSearcher = BucketSearcher(File(PARENT_DIRECTORY, "chats.txt").path).also {
    if (loadFromChat) {
        it.preProcess { buckets ->
            println("preProcess() done, exporting results...")
            Utils.exportPreprocessResult(buckets)
            println("successfully exported processed data.")
        }
    } else {
        it.load()
    }
}

@KtorExperimentalLocationsAPI
@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configureSecurity()
    configureHTTP()
    configureMonitoring()
}

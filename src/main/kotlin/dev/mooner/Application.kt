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

fun exportPreprocessResult(buckets: Map<String, String>): Set<String> {
    val directory = File(PARENT_DIRECTORY, "preprocessed").also {
        if (!it.exists() || !it.isDirectory) {
            it.mkdirs()
        }
    }
    val userNames: MutableSet<String> = hashSetOf()
    for ((name, bucket) in buckets) {
        if (name !in userNames) {
            userNames.add(name)
            File(directory, Utils.checkFileName(name) + ".bucket").writeText(bucket, Charsets.UTF_8)
        }
    }
    val encoded = Json.encodeToString(userNames)
    File(directory, "usernames.json").writeText(encoded, Charsets.UTF_8)
    return userNames
}

const val loadFromChat = false
val bucketSearcher = BucketSearcher(File(PARENT_DIRECTORY, "chats.txt").path).also {
    if (loadFromChat) {
        it.preProcess { buckets ->
            println("preProcess() done, exporting results...")
            exportPreprocessResult(buckets)
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
    configureSerialization()
}

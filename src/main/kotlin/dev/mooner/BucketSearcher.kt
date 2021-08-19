package dev.mooner

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

class BucketSearcher(
    path: String
) {

    companion object {
        private const val SPLITTER = "<SPLIT>"
    }

    //private val dateRegex = "--------------- ([0-9]*)년 ([0-9]*)월 ([0-9]*)일 (.*)요일 ---------------".toRegex()
    private val bucketRegex =
        """\[𖤐 AlphaDo 𖤐] \[(.*)] (.*) 님의 양동이[\s\S\r\n]+─[─]+┬[─]+([\s\S\r\n]+)─[─]+┴[─]+─[\s\S\r\n]+""".toRegex()
    private val file: File = File(path)
    private var logs: Array<String> = arrayOf()
    private var cachedBuckets: Map<String, String> = hashMapOf()

    fun preProcess(onComplete: (cache: Map<String, String>) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            val buckets: MutableMap<String, String> = hashMapOf()
            try {
                //var counter = 0
                println("loading file...")
                val raw = file.readText(Charsets.UTF_8)
                    .replace("\u200B", "")
                    .replace(" 님이 낚시를 시작합니다.", "")
                    .replace("\n[", "$SPLITTER[")
                println("loaded and preprocessed file")
                println("splitting chats...")
                val chats: List<String> = raw.split(SPLITTER)
                println("scanning ${chats.size} chats...")
                for (chat in chats) {
                    if (!chat.startsWith("[\uD81A\uDD10 AlphaDo \uD81A\uDD10]") || !chat.matches(bucketRegex)) continue
                    val args = bucketRegex.findAll(chat).first().groupValues.drop(1)
                    if (args.size != 3) continue
                    buckets[args[1]] = args[2]
                        .replaceFirst("\n", "───┬───────────────────\n")
                        .replaceAfterLast("\n", "───┴───────────────────")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cachedBuckets = buckets
                onComplete(buckets)
            }
        }
    }

    fun load() {
        val buckets: MutableMap<String, String> = hashMapOf()
        val directory = File(PARENT_DIRECTORY, "preprocessed")
        val usernames: Set<String> =
            Json.decodeFromString(File(directory, "usernames.json").readText(Charsets.UTF_8))

        for (name in usernames) {
            val file = File(directory, Utils.checkFileName(name) + ".bucket")
            if (file.exists() && file.isFile && file.extension == "bucket") {
                buckets[name] = file.readText(Charsets.UTF_8)
            }
        }
        this.cachedBuckets = buckets
    }

    fun get(name: String): String? = cachedBuckets[name]
}
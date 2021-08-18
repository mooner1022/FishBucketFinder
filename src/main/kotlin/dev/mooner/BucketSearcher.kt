package dev.mooner

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class BucketSearcher(
    path: String
) {

    private val dateRegex = "--------------- ([0-9]*)년 ([0-9]*)월 ([0-9]*)일 (.*)요일 ---------------".toRegex()
    private val bucketRegex = """\[𖤐 AlphaDo 𖤐] \[(.*)] (.*) 님의 양동이[\s\S\r\n]+─[─]+┬[─]+([\s\S\r\n]+)─[─]+─┴[─]+─""".toRegex()
    private val file: File = File(path)
    private var logs: Array<String> = arrayOf()
    private var cachedBuckets: HashMap<String, String> = hashMapOf()

    fun preProcess(onComplete: (cache: HashMap<String, String>) -> Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            val buckets: HashMap<String, String> = hashMapOf()
            try {
                //var counter = 0
                println("loading file...")
                val raw = file.readText(Charsets.UTF_8)
                    .replace("\u200B", "")
                    .replace(" 님이 낚시를 시작합니다.", "")
                println("loaded and preprocessed file")
                println(raw)
                println("finding matches...")
                val matches = bucketRegex.findAll(raw)
                println("saving buckets...")
                for (values in matches) {
                    val matched = values.groupValues.drop(1)
                    if (matched.size != 3) continue
                    println(matched[1])
                    buckets[matched[1]] = matched[2]
                        .replaceFirst("\n", "───┬───────────────────\n")
                        .replaceAfterLast("\n", "───┴───────────────────")
                }
                /*
                val dates = raw.split(dateRegex).drop(1)
                for (date in dates) {
                    counter++
                    println("day$counter")
                    for (values in bucketRegex.findAll(date)) {
                        val matched = values.groupValues.drop(1)
                        if (matched.size != 3) continue
                        println("day$counter: ${matched[1]}")
                        buckets[matched[1]] = matched[2]
                            .replaceFirst("\n", "───┬───────────────────\n")
                            .replaceAfterLast("\n", "───┴───────────────────")
                    }
                }
                */
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                cachedBuckets = buckets
                onComplete(buckets)
            }
        }
    }

    fun get(name: String): String? = cachedBuckets[name]
}
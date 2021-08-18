package dev.mooner

import java.io.File

class BucketSearcher(
    path: String
) {

    private val logs: Array<String> =
        File(path).readText(Charsets.UTF_8).split("\n").toTypedArray()
    private val cachedBuckets: HashMap<String, String> = hashMapOf()

    fun search(name: String): String? {
        if (cachedBuckets.containsKey(name)) return cachedBuckets[name]

        val found: String? = try {
            val found = logs.findLast {
                it.contains("$name 님의 양동이")
            }
            found
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        if (found != null) {
            cachedBuckets[name] = found
        }
        return found
    }
}
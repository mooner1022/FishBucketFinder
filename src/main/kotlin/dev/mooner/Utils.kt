package dev.mooner

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class Utils {
    companion object {
        fun exportPreprocessResult(buckets: Map<String, String>): Map<String, String> {
            val directory = File(PARENT_DIRECTORY, "preprocessed").also {
                if (!it.exists() || !it.isDirectory) {
                    it.mkdirs()
                }
            }
            val mappings: MutableMap<String, String> = hashMapOf()
            for ((name, bucket) in buckets) {
                if (!mappings.containsKey(name)) {
                    val fileName = generateCacheName(name)
                    File(directory, "$fileName.bucket").writeText(bucket, Charsets.UTF_8)
                    mappings[name] = fileName
                }
            }
            val encoded = Json.encodeToString(mappings)
            File(directory, "usernames.json").writeText(encoded, Charsets.UTF_8)
            return mappings
        }

        fun generateCacheName(name: String): String {
            val random = randomAlphanumeric(10)
            return "$random-${name.hashCode()}"
        }

        private val alphanumericPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        fun randomAlphanumeric(length: Int): String {
            val arr: ArrayList<Char> = arrayListOf()
            repeat(length) {
                arr.add(alphanumericPool.random())
            }
            return arr.joinToString("")
        }

        fun checkFileName(name: String): String {
            var fixed = name
            if (fixed.contains("$")) {
                fixed = fixed.replace("$", "dlr")
            }
            if (fixed.contains("%")) {
                fixed = fixed.replace("%", "esc")
            }
            if (fixed.contains("*")) {
                fixed = fixed.replace("*", "mp")
            }
            if (fixed.contains(".")) {
                fixed = fixed.replace(".", "dt")
            }
            if (fixed.contains(">")) {
                fixed = fixed.replace(">", "ar")
            }
            if (fixed.contains("<")) {
                fixed = fixed.replace("<", "al")
            }
            if (fixed.contains("?")) {
                fixed = fixed.replace("?", "qs")
            }
            if (fixed.contains(" ")) {
                fixed = fixed.replace(" ", "_")
            }
            if (fixed.contains(File.pathSeparator)) {
                fixed = fixed.replace(File.pathSeparator, "_")
            }
            if (fixed.contains("/")) {
                fixed = fixed.replace("/", "_")
            }
            if (fixed.contains("\\")) {
                fixed = fixed.replace("\\", "_")
            }
            return fixed
        }
    }
}
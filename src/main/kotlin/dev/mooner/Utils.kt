package dev.mooner

import java.io.File

class Utils {
    companion object {
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
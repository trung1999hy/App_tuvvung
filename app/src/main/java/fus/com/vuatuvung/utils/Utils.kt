package fus.com.vuatuvung.utils

object Utils {
    fun capitalize(str: String): String {
        return str.trim().split("\\s+".toRegex())
            .joinToString(" ") { it.capitalize() }
    }

}
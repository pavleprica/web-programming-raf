package rs.webprogramming.homework_vi.util

import java.time.Instant

object Logger {

    fun logInfo(message: String) {
        println(Instant.now().toString() + " INFO: $message")
    }

    fun logDebug(message: String) {
        println(Instant.now().toString() + " DEBUG: $message")
    }

    fun logWarn(message: String) {
        println(Instant.now().toString() + " WARN: $message")
    }

    fun logError(message: String) {
        println(Instant.now().toString() + " ERROR: $message")
    }

}
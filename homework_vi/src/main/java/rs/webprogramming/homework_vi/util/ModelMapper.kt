package rs.webprogramming.homework_vi.util

import rs.webprogramming.homework_vi.model.Assistant
import org.json.JSONObject
import org.json.JSONTokener

fun String.mapToAssistant(): Assistant {
    val jsonToken = JSONTokener(this)
    val requestPayload = JSONObject(jsonToken)

    return Assistant(
            requestPayload.optString("name", "NO_NAME").toUpperCase(),
            requestPayload.optInt("score", -1),
            null
    )
}
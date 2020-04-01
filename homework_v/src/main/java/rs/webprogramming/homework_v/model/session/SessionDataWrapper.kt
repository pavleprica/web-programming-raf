package rs.webprogramming.homework_v.model.session

import java.io.Serializable

data class SessionDataWrapper(val key: String, val data: String) {
    override fun toString(): String {
        return "key: $key :: data: $data"
    }
}
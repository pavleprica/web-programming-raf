package rs.webprogramming.homework_v.model.request

import java.io.Serializable

data class RequestDataWrapper(val key: String, val data: String) {
    override fun toString(): String {
        return "key: $key :: data: $data"
    }
}
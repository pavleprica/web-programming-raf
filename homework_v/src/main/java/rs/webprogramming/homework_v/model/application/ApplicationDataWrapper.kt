package rs.webprogramming.homework_v.model.application

import java.io.Serializable

data class ApplicationDataWrapper(val key: String, val data: String) {

    override fun toString(): String {
        return "key: $key :: data: $data"
    }
}
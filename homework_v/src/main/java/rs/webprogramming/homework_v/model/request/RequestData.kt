package rs.webprogramming.homework_v.model.request

import java.io.Serializable

class RequestData: Serializable {

    private var requestData: MutableSet<RequestDataWrapper>? = null

    init {
        requestData = mutableSetOf()
    }

    fun getRequestData(): MutableSet<RequestDataWrapper>? {
        return requestData
    }

    fun setRequestData(requestData: MutableSet<RequestDataWrapper>?) {
        this.requestData = requestData
    }
}
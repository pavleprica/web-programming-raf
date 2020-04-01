package rs.webprogramming.homework_v.model.session

import java.io.Serializable

class SessionData(): Serializable {

    private var sessionData: MutableSet<SessionDataWrapper>? = null

    init {
        sessionData = mutableSetOf()
    }

    fun getSessionData(): MutableSet<SessionDataWrapper>? {
        return sessionData
    }

    fun setSessionData(sessionData: MutableSet<SessionDataWrapper>?) {
        this.sessionData = sessionData
    }

}
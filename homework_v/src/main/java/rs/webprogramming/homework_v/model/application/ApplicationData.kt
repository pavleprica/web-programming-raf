package rs.webprogramming.homework_v.model.application

import java.io.Serializable

class ApplicationData: Serializable {

    private var applicationData: MutableSet<ApplicationDataWrapper>? = null

    init {
        applicationData = mutableSetOf()
    }

    fun getApplicationData(): MutableSet<ApplicationDataWrapper>? {
        return applicationData
    }

    fun setApplicationData(applicationData: MutableSet<ApplicationDataWrapper>?) {
        this.applicationData = applicationData
    }

}
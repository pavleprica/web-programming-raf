package rs.webprogramming.homework_vi.controller

import rs.webprogramming.homework_vi.repository.AssistantRepository
import rs.webprogramming.homework_vi.repository.MySqlAssistantRepository
import rs.webprogramming.homework_vi.service.DefaultRestResponseService
import rs.webprogramming.homework_vi.service.RestResponseService
import rs.webprogramming.homework_vi.util.mapToAssistant
import java.io.BufferedReader
import javax.servlet.Servlet
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/grading")
class AssistantController: HttpServlet(), Servlet {

    private val restResponseService: RestResponseService = DefaultRestResponseService()

    override fun doPost(request: HttpServletRequest?, response: HttpServletResponse?) {
        val buffer = StringBuilder()
        val reader: BufferedReader = request?.reader!!
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            buffer.append(line)
        }
        val payload = buffer.toString()

        val assistant = payload.mapToAssistant()

        if(assistant.score!! < 1 || assistant.score > 10) {
            restResponseService.invalidScore(response!!, assistant)
            return
        }

        restResponseService.saveAssistant(request, response!!, assistant)
    }

}
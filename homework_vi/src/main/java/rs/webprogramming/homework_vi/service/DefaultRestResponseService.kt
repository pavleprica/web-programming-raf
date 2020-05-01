package rs.webprogramming.homework_vi.service

import rs.webprogramming.homework_vi.model.Assistant
import org.json.JSONObject
import rs.webprogramming.homework_vi.repository.AssistantRepository
import rs.webprogramming.homework_vi.repository.MySqlAssistantRepository
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class DefaultRestResponseService: RestResponseService {

    private val assistantRepository: AssistantRepository = MySqlAssistantRepository()

    override fun invalidScore(response: HttpServletResponse, assistant: Assistant) {
        val jsonResponse = JSONObject()

        jsonResponse.put("httpResponse", 400)
        jsonResponse.put("successful", false)
        jsonResponse.put("errorMessage", "Invalid score: [${assistant.score}] - score must be in range [1, 10]")

        response.writer.println(jsonResponse.toString())
        response.contentType = "application/json"
        response.status = 400
    }

    override fun saveAssistant(request: HttpServletRequest, response: HttpServletResponse, assistant: Assistant) {
        assistantRepository.saveNewAssistant(assistant)

        val jsonResponse = JSONObject()

        jsonResponse.put("httpResponse", 200)
        jsonResponse.put("successful", true)

        response.writer.println(jsonResponse.toString())
        response.contentType = "application/json"
        response.status = 200

        val requestDispatcher = request.getRequestDispatcher("/assistants.jsp")
        requestDispatcher.forward(request, response)
    }

}
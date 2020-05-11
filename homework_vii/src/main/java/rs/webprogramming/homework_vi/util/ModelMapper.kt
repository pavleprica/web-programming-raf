package rs.webprogramming.homework_vi.util

import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import rs.webprogramming.homework_vi.model.Ticket
import rs.webprogramming.homework_vi.model.TicketSaveStatus
import rs.webprogramming.homework_vi.repository.AviationCompanyRepository
import rs.webprogramming.homework_vi.repository.AviationCompanyRepositoryMySql
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


fun List<Ticket>.mapToJson(): String {
    val response = JSONArray()

    for(ticket in this) {
        val ticketObject = JSONObject()
        ticketObject.put("oneWay", ticket.oneWay)
        ticketObject.put("from", ticket.from)
        ticketObject.put("to", ticket.to)
        ticketObject.put("aviationCompanyName", ticket.aviationCompany.name)
        ticketObject.put("departDate", ticket.departDate)
        ticketObject.put("returnDate", ticket.returnDate)
        response.put(ticketObject)
    }

    return response.toString()
}

fun String.mapToTicket(): Ticket {
    val token = JSONTokener(this)
    val requestPayload = JSONObject(token)

    val aviationCompanyRepository: AviationCompanyRepository = AviationCompanyRepositoryMySql()

    val aviationCompany =
            aviationCompanyRepository.findById(requestPayload.getLong("aviationCompanyId"))

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    var temporalAccessor = formatter.parse(requestPayload.getString("departDate"))
    var localDateTime = LocalDateTime.from(temporalAccessor)
    var zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault())
    val departDate = Instant.from(zonedDateTime)

    val returnDateString = requestPayload.optString("returnDate")

    return if(returnDateString == null || returnDateString == "") {
        Ticket(
                -1L,
                requestPayload.getBoolean("oneWay"),
                requestPayload.getString("from"),
                requestPayload.getString("to"),
                departDate,
                null,
                aviationCompany
        )
    } else {
        temporalAccessor = formatter.parse(returnDateString)
        localDateTime = LocalDateTime.from(temporalAccessor)
        zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault())
        val returnDate = Instant.from(zonedDateTime)

        Ticket(
                -1L,
                requestPayload.getBoolean("oneWay"),
                requestPayload.getString("from"),
                requestPayload.getString("to"),
                departDate,
                returnDate,
                aviationCompany
        )
    }
}

fun TicketSaveStatus.mapToJson(): String {
    val response = JSONObject()

    response.put("status", this.status)
    response.put("message", this.message)

    return response.toString()
}
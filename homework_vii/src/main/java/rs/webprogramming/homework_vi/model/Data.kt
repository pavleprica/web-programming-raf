package rs.webprogramming.homework_vi.model

import java.time.Instant

data class AviationCompany(
        val id: Long,
        val name: String,
        val version: Long
)

data class Ticket(
        val id: Long,
        val oneWay: Boolean,
        val from: String,
        val to: String,
        val departDate: Instant,
        val returnDate: Instant?,
        val aviationCompany: AviationCompany
)

data class TicketSaveStatus(
        val status: Boolean,
        val message: String? = null
)
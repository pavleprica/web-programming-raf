package rs.webprogramming.homework_vi.repository

import rs.webprogramming.homework_vi.model.Ticket
import rs.webprogramming.homework_vi.model.TicketSaveStatus

interface TicketRepository {

    fun getAll(): List<Ticket>

    fun findAllTicketsForOneWay(oneWay: Boolean): List<Ticket>

    fun findAllTicketsForAviationCompany(aviationCompanyId: Long, filterForOneWay: Boolean = false, oneWay: Boolean = false): List<Ticket>

    fun findById(id: Long): Ticket

    fun save(ticket: Ticket): TicketSaveStatus

}
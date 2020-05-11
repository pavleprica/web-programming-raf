package rs.webprogramming.homework_vi.controller

import rs.webprogramming.homework_vi.model.Ticket
import rs.webprogramming.homework_vi.model.TicketSaveStatus
import rs.webprogramming.homework_vi.repository.TicketRepository
import rs.webprogramming.homework_vi.repository.TicketRepositoryMySql
import rs.webprogramming.homework_vi.util.mapToJson
import rs.webprogramming.homework_vi.util.mapToTicket
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/tickets")
class TicketListController: HttpServlet() {

    private val ticketRepository: TicketRepository = TicketRepositoryMySql()

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val oneWayParam = req!!.getParameter("oneWay")

        val oneWay =
                if (oneWayParam == null) {
                    null
                } else {
                    oneWayParam == "true"
                }

        val tickets = if(oneWay != null) {
            ticketRepository.findAllTicketsForOneWay(oneWay)
        } else {
            ticketRepository.getAll()
        }

        resp!!.writer.println(tickets.mapToJson())
    }

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val requestPayload = req!!.reader.readLine().mapToTicket()

        val saveStatus = ticketRepository.save(requestPayload)

        resp!!.writer.println(saveStatus.mapToJson())
    }


}
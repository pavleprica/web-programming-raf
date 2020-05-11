package rs.webprogramming.homework_vi.repository

import rs.webprogramming.homework_vi.model.Ticket
import rs.webprogramming.homework_vi.model.TicketSaveStatus
import rs.webprogramming.homework_vi.util.Logger
import java.lang.Exception
import java.sql.PreparedStatement
import java.sql.Timestamp
import java.time.Instant

class TicketRepositoryMySql: TicketRepository {

    companion object {
        private val GET_ALL_QUERY = "SELECT * FROM `ticket`"
        private val FIND_ALL_FOR_ONEWAY =
                "SELECT * FROM `ticket` WHERE `one_way` = ?"
        private val FIND_ALL_FOR_AVIO_COMPANY_NO_ONEWAY_FILTER =
                "SELECT * FROM `ticket` WHERE `aviation_company_id` = ?"
        private val FIND_ALL_FOR_AVIO_COMPANY_ONEWAY_FILTER =
                "SELECT * FROM `ticket` WHERE `aviation_company_id` = ? AND `one_way` = ?"
        private val FIND_BY_ID_QUERY = "SELECT * FROM `ticket` WHERE `id` = ?"
        private val SAVE_QUERY = "INSERT INTO `ticket` " +
                "(`one_way`, `to`, `from`, `depart_date`, `return_date`, `aviation_company_id`) " +
                "VALUES (?, ?, ?, ?, ?, ?)"
    }

    private val aviationCompanyRepository: AviationCompanyRepository =
            AviationCompanyRepositoryMySql()


    override fun getAll(): List<Ticket> {
        val aviationCompanies =
                aviationCompanyRepository.getAll().map { it.id to it }.toMap()

        val connection = DatabaseHandler.openConnection()

        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(GET_ALL_QUERY)

        val ticketsList: MutableList<Ticket> = mutableListOf()
        while (resultSet.next()) {
            ticketsList.add(
                    Ticket(
                            resultSet.getLong("id"),
                            resultSet.getBoolean("one_way"),
                            resultSet.getString("to"),
                            resultSet.getString("from"),
                            resultSet.getTimestamp("depart_date").toInstant(),
                            resultSet.getTimestamp("return_date")?.toInstant(),
                            aviationCompanies.getValue(resultSet.getLong("aviation_company_id"))
                    )
            )
        }

        connection.close()
        return ticketsList
    }

    override fun findAllTicketsForOneWay(oneWay: Boolean): List<Ticket> {
        val aviationCompanies =
                aviationCompanyRepository.getAll().map { it.id to it }.toMap()

        val connection = DatabaseHandler.openConnection()

        val statement: PreparedStatement

        statement = connection.prepareStatement(FIND_ALL_FOR_ONEWAY)
        statement.setBoolean(1, oneWay)

        val resultSet = statement.executeQuery()

        val ticketsList: MutableList<Ticket> = mutableListOf()
        while(resultSet.next()) {
            ticketsList.add(
                    Ticket(
                            resultSet.getLong("id"),
                            resultSet.getBoolean("one_way"),
                            resultSet.getString("to"),
                            resultSet.getString("from"),
                            resultSet.getTimestamp("depart_date").toInstant(),
                            resultSet.getTimestamp("return_date")?.toInstant(),
                            aviationCompanies.getValue(resultSet.getLong("aviation_company_id"))
                    )
            )
        }

        connection.close()
        return ticketsList
    }

    override fun findAllTicketsForAviationCompany(aviationCompanyId: Long, filterForOneWay: Boolean, oneWay: Boolean): List<Ticket> {
        val aviationCompanies =
                aviationCompanyRepository.getAll().map { it.id to it }.toMap()

        val connection = DatabaseHandler.openConnection()

        var statement: PreparedStatement

        if(filterForOneWay) {
            statement = connection.prepareStatement(FIND_ALL_FOR_AVIO_COMPANY_ONEWAY_FILTER)
            statement.setLong(1, aviationCompanyId)
            statement.setBoolean(2, oneWay)

        } else {
            statement = connection.prepareStatement(FIND_ALL_FOR_AVIO_COMPANY_NO_ONEWAY_FILTER)
            statement.setLong(1, aviationCompanyId)
        }

        val resultSet = statement.executeQuery()

        val ticketsList: MutableList<Ticket> = mutableListOf()
        while(resultSet.next()) {
            ticketsList.add(
                    Ticket(
                            resultSet.getLong("id"),
                            resultSet.getBoolean("one_way"),
                            resultSet.getString("to"),
                            resultSet.getString("from"),
                            resultSet.getTimestamp("depart_date").toInstant(),
                            resultSet.getTimestamp("return_date")?.toInstant(),
                            aviationCompanies.getValue(resultSet.getLong("aviation_company_id"))
                    )
            )
        }

        connection.close()
        return ticketsList
    }

    override fun findById(id: Long): Ticket {
        val connection = DatabaseHandler.openConnection()

        val statement = connection.prepareStatement(FIND_BY_ID_QUERY)
        statement.setLong(1, id)

        val resultSet = statement.executeQuery()

        if(resultSet.next()) {

            val ticket = Ticket(
                        resultSet.getLong("id"),
                        resultSet.getBoolean("one_way"),
                        resultSet.getString("to"),
                        resultSet.getString("from"),
                        resultSet.getTimestamp("depart_date").toInstant(),
                        resultSet.getTimestamp("return_date")?.toInstant(),
                        aviationCompanyRepository.findById(resultSet.getLong("aviation_company_id"))
                )

            connection.close()

            return ticket
        } else {
            val errorMessage = "No ticket found with id: $id"
            Logger.logError(errorMessage)
            throw Exception(errorMessage)
        }
    }

    override fun save(ticket: Ticket): TicketSaveStatus {
        val connection = DatabaseHandler.openConnection()

        return if(!aviationCompanyRepository.checkVersionCompatibility(
                        ticket.aviationCompany.id,
                        ticket.aviationCompany.version
                )) {

            TicketSaveStatus(false, "Aviation company has been modified")
        } else {

            val returnDateTimestamp = if(ticket.returnDate == null) null else Timestamp.from(ticket.returnDate)

            if(ticket.from == ticket.to) {
                return TicketSaveStatus(false, "From and to can't be a same location")
            }

            if(returnDateTimestamp != null) {
                if(returnDateTimestamp.toInstant().isBefore(ticket.departDate)) {
                    return TicketSaveStatus(false, "Timestamp error, return can't be before depart")
                }
            }

            val statement = connection.prepareStatement(SAVE_QUERY)
            statement.setBoolean(1, ticket.oneWay)
            statement.setString(2, ticket.to)
            statement.setString(3, ticket.from)
            statement.setTimestamp(4, Timestamp.from(ticket.departDate))
            statement.setTimestamp(5, returnDateTimestamp)
            statement.setLong(6, ticket.aviationCompany.id)

            statement.executeUpdate()

            connection.close()

            TicketSaveStatus(true)
        }
    }
}
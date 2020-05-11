package rs.webprogramming.homework_vi.repository

import rs.webprogramming.homework_vi.model.AviationCompany
import rs.webprogramming.homework_vi.util.Logger
import java.lang.Exception

class AviationCompanyRepositoryMySql: AviationCompanyRepository {

    companion object {
        private val GET_ALL_QUERY = "SELECT * FROM `aviation_company`"
        private val FIND_BY_ID_QUERY = "SELECT * FROM `aviation_company` WHERE `id` = ?"
        private val SAVE_QUERY = "INSERT INTO `aviation_company` (`name`, `version`) VALUES (?, ?)"
        private val CHECK_VERSION_QUERY = "SELECT `version` FROM `aviation_company` WHERE `id` = ?"
    }

    override fun getAll(): List<AviationCompany> {
        val connection = DatabaseHandler.openConnection()

        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(GET_ALL_QUERY)

        val aviationList: MutableList<AviationCompany> = mutableListOf()
        while (resultSet.next()) {
            aviationList.add(
                    AviationCompany(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getLong("version")
                    )
            )
        }

        connection.close()
        return aviationList
    }

    override fun findById(id: Long): AviationCompany {
        val connection = DatabaseHandler.openConnection()

        val statement = connection.prepareStatement(FIND_BY_ID_QUERY)
        statement.setLong(1, id)
        val resultSet = statement.executeQuery()

        if(resultSet.next()) {
            val aviationCompany = AviationCompany(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getLong("version")
            )

            connection.close()

            return aviationCompany
        } else {
            val errorMessage = "No aviation company found with id: $id"
            Logger.logError(errorMessage)
            throw Exception(errorMessage)
        }
    }

    override fun save(aviationCompany: AviationCompany) {
        val connection = DatabaseHandler.openConnection()

        val statement = connection.prepareStatement(SAVE_QUERY)
        statement.setString(1, aviationCompany.name)
        statement.setLong(2, aviationCompany.version)

        statement.executeUpdate()

        connection.close()
    }

    override fun checkVersionCompatibility(id: Long, version: Long): Boolean {
        val connection = DatabaseHandler.openConnection()

        val statement = connection.prepareStatement(CHECK_VERSION_QUERY)
        statement.setLong(1, id)

        val resultSet = statement.executeQuery()

        return if(resultSet.next()) {
            val persistedVersion = resultSet.getLong("version")
            connection.close()
            persistedVersion == version
        } else {
            connection.close()
            false
        }

    }
}
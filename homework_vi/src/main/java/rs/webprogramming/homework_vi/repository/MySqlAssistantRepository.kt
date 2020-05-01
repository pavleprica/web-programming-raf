package rs.webprogramming.homework_vi.repository

import rs.app.povezi.util.Logger
import rs.webprogramming.homework_vi.model.Assistant
import java.sql.Connection
import java.sql.DriverManager

class MySqlAssistantRepository: AssistantRepository {

    companion object {
        private val host = "jdbc:mysql://localhost:3306/homework"
        private val username = "root"
        private val password = "my-secret-pw"

        private val nullableAssistant = "ALEKSANDAR"

        private val GET_AVERAGE_QUERY = "SELECT `name`, SUM(score) AS score_sum, COUNT(score) AS grade_count FROM assistant_grades GROUP BY name"
        private val INSERT_QUERY =
                "INSERT INTO assistant_grades (name, score) VALUES (?, ?)"
    }

    private fun openConnection(): Connection {
        Class.forName("com.mysql.cj.jdbc.Driver")
        var connection = DriverManager.getConnection(
                host, username, password
        )
        return connection
    }

    override fun getAssistantAverage(): MutableList<Assistant> {
        Logger.logInfo("Fetch data for assistant grading")
        val connection = openConnection()

        val statement = connection.createStatement()
        val resultSet = statement.executeQuery(GET_AVERAGE_QUERY)

        val assistantsGrading = mutableListOf<Assistant>()

        while(resultSet.next()) {
            val scoreSum = resultSet.getInt("score_sum")
            val gradeCount = resultSet.getInt("grade_count")

            var averageScore: Float

            averageScore = if(resultSet.getString("name") == nullableAssistant) {
                0F
            } else {
                (scoreSum.toFloat()) / gradeCount
            }

            assistantsGrading.add(
                    Assistant(
                            resultSet.getString("name"),
                            null,
                            averageScore
                    )
            )
        }

        connection.close()

        Logger.logInfo("Assistant summary: $assistantsGrading")

        return assistantsGrading
    }

    override fun saveNewAssistant(assistant: Assistant) {
        val connection = openConnection()

        val statement = connection.prepareStatement(INSERT_QUERY)
        statement.setString(1, assistant.name)
        statement.setInt(2, assistant.score!!)

        statement.execute()

        connection.close()
    }


}
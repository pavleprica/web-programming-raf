package rs.webprogramming.homework_vi.repository

import java.sql.Connection
import java.sql.DriverManager

object DatabaseHandler {

    private val host = "jdbc:mysql://localhost:3306/homework"
    private val username = "root"
    private val password = "my-secret-pw"

    fun openConnection(): Connection {
        Class.forName("com.mysql.cj.jdbc.Driver")
        var connection = DriverManager.getConnection(
                host, username, password
        )
        return connection
    }

}
package homework_iv.database.repository;

import homework_iv.database.model.Assistant;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MySqlAssistantRepository implements AssistantRepository {

    private static final String databaseUrl = "jdbc:mysql://localhost:3306/homework";
    private static final String username = "root";
    private static final String password = "my-secret-pw";

    private static final String nullableAssistant = "ALEKSANDAR";

    private static final String insertQuery = "insert into assistant_grades (name, score) values (?, ?)";

    @SneakyThrows
    public List<Assistant> getAssistantAverage() {
        log.info("Fetching data for assistant grading.");
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(databaseUrl, username, password);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "select name, sum(score) as score_sum, count(score) as grade_count from assistant_grades group by name");

        List<Assistant> assistantsGrading = new ArrayList<>();

        while(resultSet.next()) {
            int scoreSum = resultSet.getInt("score_sum");
            int gradeCount = resultSet.getInt("grade_count");

            assistantsGrading.add(
                    Assistant.builder()
                    .name(resultSet.getString("name"))
                    .averageScore(resultSet.getString("name").equals(nullableAssistant) ?
                            0 : ((float) scoreSum) / gradeCount)
                    .build()
            );
        }

        connection.close();

        log.info("Assistant summary: {}", assistantsGrading);

        return assistantsGrading;
    }

    @Override
    @SneakyThrows
    public void saveNewAssistant(Assistant assistant) {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(databaseUrl, username, password);

        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, assistant.getName());
        preparedStatement.setInt(2, assistant.getScore());

        preparedStatement.execute();

        connection.close();
    }
}

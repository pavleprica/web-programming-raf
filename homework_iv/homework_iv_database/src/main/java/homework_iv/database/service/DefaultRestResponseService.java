package homework_iv.database.service;

import homework_iv.database.model.Assistant;
import homework_iv.database.repository.AssistantRepository;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class DefaultRestResponseService implements RestResponseService {

    private final AssistantRepository assistantRepository;

    public DefaultRestResponseService(AssistantRepository assistantRepository) {
        this.assistantRepository = assistantRepository;
    }

    @Override
    @SneakyThrows
    public void saveAssistant(BufferedReader socketIn, PrintWriter socketOut) {
        JSONTokener token = new JSONTokener(socketIn.readLine());
        JSONObject request = new JSONObject(token);

        Assistant newEntry = Assistant.builder()
                .name(request.getString("name"))
                .score(request.getInt("score"))
                .build();

        assistantRepository.saveNewAssistant(newEntry);

        socketOut.println("saved");
    }

    @Override
    public void listAssistants(PrintWriter socketOut) {
        JSONObject response = new JSONObject();

        assistantRepository.getAssistantAverage().forEach(assistant -> {
            JSONObject assistantObject = new JSONObject();
            assistantObject.put("name", assistant.getName());
            assistantObject.put("averageScore", assistant.getAverageScore());
            response.append("assistants", assistantObject);
        });

        socketOut.println(response.toString());
        socketOut.println("listed");
    }
}

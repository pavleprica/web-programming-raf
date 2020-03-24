package homework_iv.database.repository;


import homework_iv.database.model.Assistant;

import java.util.List;

public interface AssistantRepository {

    List<Assistant> getAssistantAverage();

    void saveNewAssistant(Assistant assistant);

}

package rs.webprogramming.homework_vi.repository;


import org.jetbrains.annotations.NotNull;
import rs.webprogramming.homework_vi.model.Assistant;

import java.util.List;

public interface AssistantRepository {

    List<Assistant> getAssistantAverage();

    void saveNewAssistant(@NotNull Assistant assistant);

}

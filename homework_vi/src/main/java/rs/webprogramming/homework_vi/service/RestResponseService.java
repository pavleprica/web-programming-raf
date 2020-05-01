package rs.webprogramming.homework_vi.service;

import org.jetbrains.annotations.NotNull;
import rs.webprogramming.homework_vi.model.Assistant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public interface RestResponseService {

    void invalidScore(@NotNull HttpServletResponse response, @NotNull Assistant assistant);

    void saveAssistant(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Assistant assistant);

}

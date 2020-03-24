package rs.webprogramming.homework_iv.main.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import rs.webprogramming.homework_iv.main.service.DatabaseService;
import rs.webprogramming.homework_iv.main.service.DefaultDatabaseService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;

@Slf4j
@WebServlet("/assistant-to-grade")
public class AssistantController extends HttpServlet {

    private final DatabaseService databaseService = new DefaultDatabaseService();

    @SneakyThrows
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setStatus(200);

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String payload = buffer.toString();

        databaseService.saveAssistant(payload, out);

        out.close();
    }

    @SneakyThrows
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setStatus(200);
        databaseService.listAssistants(out);
        out.close();
    }

}

package rs.webprogramming.homework_iv.main.service;

import java.io.PrintWriter;

public interface DatabaseService {

    void saveAssistant(String request, PrintWriter out);

    void listAssistants(PrintWriter out);

}

package homework_iv.database.service;

import java.io.BufferedReader;
import java.io.PrintWriter;

public interface RestResponseService {

    void saveAssistant(BufferedReader socketIn, PrintWriter socketOut);

    void listAssistants(PrintWriter socketOut);

}

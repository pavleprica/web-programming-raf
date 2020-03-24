package rs.webprogramming.homework_iv.main.service;

import lombok.SneakyThrows;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class DefaultDatabaseService implements DatabaseService {

    @Override
    @SneakyThrows
    public void saveAssistant(String request, PrintWriter out) {
        JSONTokener token = new JSONTokener(request);
        JSONObject requestObject = new JSONObject(token);
        requestObject.put("name", requestObject.getString("name").toUpperCase());

        Socket socket = new Socket("localhost", 8081);

        PrintWriter socketOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        socketOut.println("save");
        socketOut.println(requestObject.toString());
        socketOut.close();

        JSONObject response = new JSONObject();
        response.put("successful", true);
        out.println(response.toString());
        socket.close();
    }

    @Override
    @SneakyThrows
    public void listAssistants(PrintWriter out) {
        Socket socket = new Socket("localhost", 8081);

        PrintWriter socketOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        socketOut.println("list");

        JSONTokener token = new JSONTokener(socketIn.readLine());
        JSONObject response = new JSONObject(token);

        out.println(response.toString(2));

        socketIn.close();
        socket.close();
    }
}

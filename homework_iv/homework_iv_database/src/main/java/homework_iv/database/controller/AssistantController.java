package homework_iv.database.controller;

import homework_iv.database.repository.MySqlAssistantRepository;
import homework_iv.database.service.DefaultRestResponseService;
import lombok.SneakyThrows;

import java.net.ServerSocket;
import java.net.Socket;

public class AssistantController {

    private static final String endpoint = "/assistants";

    @SneakyThrows
    public static void main(String[] args) {

        ServerSocket serverSocket = new ServerSocket(8081);

        while(true) {
            Socket socket = serverSocket.accept();
            new Thread(
                    new AssistantHandler(
                            socket,
                            endpoint,
                            new DefaultRestResponseService(new MySqlAssistantRepository()))
            ).start();
        }

    }
}

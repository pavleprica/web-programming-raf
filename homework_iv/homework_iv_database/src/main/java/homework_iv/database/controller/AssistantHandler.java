package homework_iv.database.controller;


import homework_iv.database.service.RestResponseService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class AssistantHandler implements Runnable {

    private final Socket socket;
    private final String endpointUrl;
    private final RestResponseService restResponseService;

    public AssistantHandler(Socket socket, String endpointUrl, RestResponseService restResponseService) {
        this.socket = socket;
        this.endpointUrl = endpointUrl;
        this.restResponseService = restResponseService;
    }

    @Override
    @SneakyThrows
    public void run() {
        BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        String requestMessage = socketIn.readLine();
        log.info("New request made from: {}, request: {}", socket.getInetAddress(), requestMessage);

        if(requestMessage.equals("save")) {
            restResponseService.saveAssistant(socketIn, socketOut);
        } else if(requestMessage.equals("list")) {
            restResponseService.listAssistants(socketOut);
        }

        socketIn.close();
        socketOut.close();
        socket.close();
    }
}

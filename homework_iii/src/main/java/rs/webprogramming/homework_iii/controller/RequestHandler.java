package rs.webprogramming.homework_iii.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class RequestHandler {

    private static String quoteUrl = "/quote";
    private static String redirectUrl = "/your-quote-of-the-day";
    private static String method = "GET";

    @SneakyThrows
    public static void main(String[] args) {

        ServerSocket serverSocket = new ServerSocket(8080);

        while(true) {
            Socket socket = serverSocket.accept();
            new Thread(
                    new QuoteOfTheDayController(socket, quoteUrl, redirectUrl, method)
            ).start();
        }
    }

}

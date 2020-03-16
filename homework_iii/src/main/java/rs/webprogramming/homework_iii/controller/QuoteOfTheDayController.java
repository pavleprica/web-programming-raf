package rs.webprogramming.homework_iii.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import rs.webprogramming.homework_iii.service.DefaultHttpResponseService;
import rs.webprogramming.homework_iii.service.HttpResponseService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class QuoteOfTheDayController implements Runnable {

    private final Socket socket;

    private final String quoteUrl;
    private final String redirectUrl;
    private final String method;

    private final HttpResponseService httpResponseService = new DefaultHttpResponseService();

    public QuoteOfTheDayController(Socket socket, String quoteUrl, String redirectUrl, String method) {
        this.socket = socket;
        this.quoteUrl = quoteUrl;
        this.redirectUrl = redirectUrl;
        this.method = method;
    }

    @Override
    @SneakyThrows
    public void run() {
        BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        String requestMessage = socketIn.readLine();
        log.info("New request made from: {}, request: {}", socket.getInetAddress(), requestMessage);

        if(assertUrl(requestMessage.split(" ")[1])) {
            if(assertMethod(requestMessage.split(" ")[0])) {
                if(requestMessage.split(" ")[1].equals(quoteUrl))
                    httpResponseService.redirectToQuote(socketOut, redirectUrl);
                else if(requestMessage.split(" ")[1].equals(redirectUrl))
                    httpResponseService.returnQuote(socketOut);
                else
                    httpResponseService.returnBadRequest(socketOut);
            } else {
                log.warn("Wrong method used. Used: {}", requestMessage.split(" ")[0]);
                httpResponseService.returnBadRequest(socketOut);
            }
        } else {
            log.warn("Access tried to: {}", requestMessage.split(" ")[1]);
            httpResponseService.returnNotFound(socketOut);
        }


        socketIn.close();
        socketOut.close();
        socket.close();
    }

    private boolean assertUrl(String url) {
        return url.equals(quoteUrl) || url.equals(redirectUrl);
    }

    private boolean assertMethod(String method) {
        return method.equals(this.method);
    }
}

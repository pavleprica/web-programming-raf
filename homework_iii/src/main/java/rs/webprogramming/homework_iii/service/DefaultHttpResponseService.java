package rs.webprogramming.homework_iii.service;

import lombok.SneakyThrows;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

/**
 * Default basic implementation of the responses for the http responses. Keep in mind that it might not work on
 * every system and that the path to static is defined from the working directory.
 */
public class DefaultHttpResponseService implements HttpResponseService {

    private static final String pathToStatic =
            "." + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static";

    private static final String pathForQuote = "https://quotes.rest/qod";

    @Override
    @SneakyThrows
    public void returnNotFound(PrintWriter socketOut) {
        socketOut.println("HTTP/1.1 404 Not Found");
        socketOut.println("Connection: close");
        socketOut.println("Content-Type: text/html");
        socketOut.println("\n");

        File file = new File(pathToStatic + File.separator + "404Response.html");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line;
        while((line = bufferedReader.readLine()) != null) {
            socketOut.println(line);
        }

        bufferedReader.close();
    }

    @Override
    @SneakyThrows
    public void returnBadRequest(PrintWriter socketOut) {
        socketOut.println("HTTP/1.1 400 Bad Request");
        socketOut.println("Connection: close");
        socketOut.println("Content-Type: text/html");
        socketOut.println("\n");

        File file = new File(pathToStatic + File.separator + "400Response.html");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String line;
        while((line = bufferedReader.readLine()) != null) {
            socketOut.println(line);
        }

        bufferedReader.close();
    }

    @Override
    public void redirectToQuote(PrintWriter socketOut, String redirectUrl) {
        socketOut.println("HTTP/1.1 301 Moved Permanently");
        socketOut.println("Location: /your-quote-of-the-day");
        socketOut.println("\n");
    }

    @Override
    public void returnQuote(PrintWriter socketOut) throws Exception {
        HttpGet request = new HttpGet(pathForQuote);
        request.setHeader("Accept", "application/json");

        JSONObject responseObject;

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(request)) {

            String responseContent = EntityUtils.toString(response.getEntity());
            JSONTokener responseToken = new JSONTokener(responseContent);

            responseObject = new JSONObject(responseToken);

            socketOut.println("HTTP/1.1 200 OK");
            socketOut.println("Connection: close");
            socketOut.println("Content-Type: text/html");
            socketOut.println("\n");

            File file = new File(pathToStatic + File.separator + "200Response.html");
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                if(line.equals("{quote}")) {
                    socketOut.println(responseObject
                            .getJSONObject("contents")
                            .getJSONArray("quotes")
                            .getJSONObject(0)
                            .getString("quote"));
                }
                else
                    socketOut.println(line);
            }

            bufferedReader.close();
        }
    }
}

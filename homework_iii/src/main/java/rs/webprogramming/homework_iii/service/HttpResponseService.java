package rs.webprogramming.homework_iii.service;

import java.io.PrintWriter;

/**
 * This service will handle all necessary possible responses for the homework.
 */
public interface HttpResponseService {

    void returnNotFound(PrintWriter socketOut);

    void returnBadRequest(PrintWriter socketOut);

    void redirectToQuote(PrintWriter socketOut, String redirectUrl);

    void returnQuote(PrintWriter socketOut) throws Exception;

}

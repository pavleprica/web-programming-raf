package raf.webprogramming.homework_i.service;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class PrintService {

    private static PrintService instance = null;

    private static ArrayList<String> printListProfessor;
    private static ArrayList<String> printListAssistant;

    private PrintService() {

    }

    public static PrintService getInstance() {
        if (instance == null) {
            instance = new PrintService();
            instance.initialise();
        }
        return instance;
    }

    private void initialise() {
        printListProfessor = new ArrayList<>();
        printListAssistant = new ArrayList<>();
    }

    public void updatePrintListProfessor(String line) {
        synchronized (this) {
            printListProfessor.add(line);
        }
    }

    public void updatePrintListAssistant(String line) {
        synchronized (this) {
            printListAssistant.add(line);
        }
    }

    public void printLists() {
        printListProfessor.forEach(log::info);
        printListAssistant.forEach(log::info);
    }

}

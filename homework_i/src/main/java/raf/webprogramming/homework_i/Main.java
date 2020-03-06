package raf.webprogramming.homework_i;

import raf.webprogramming.homework_i.service.PrintService;
import raf.webprogramming.homework_i.threads.AssignToQueue;
import raf.webprogramming.homework_i.service.StudentQueueService;

public class Main {

    private static final int studentSumNumber = 10;

    public static void main(String[] args) throws InterruptedException {
        StudentQueueService.getInstance(studentSumNumber);
        PrintService.getInstance();

        new Thread(new AssignToQueue()).start();
    }

}

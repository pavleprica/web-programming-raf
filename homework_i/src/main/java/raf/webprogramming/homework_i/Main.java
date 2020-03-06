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


//        for (int i = 0; i < studentSumNumber; i++) {
//            new Thread(
//                    new ProfessorQueueService(barrier,
//                    StudentQueueService.getInstance().getStudentQueue().pop())
//            ).start();
//            new Thread(
//                    new AssistantQueueService(semaphore,
//                            StudentQueueService.getInstance().getStudentQueue().pop())
//            ).start();
//            Thread.sleep(1000);
//        }
    }

}

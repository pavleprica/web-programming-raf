package raf.webprogramming.homework_i.threads;

import lombok.extern.slf4j.Slf4j;
import raf.webprogramming.homework_i.model.Student;
import raf.webprogramming.homework_i.service.PrintService;
import raf.webprogramming.homework_i.service.StudentQueueService;

import java.util.Random;
import java.util.concurrent.Semaphore;

@Slf4j
public class AssistantQueue implements Runnable {

    private Semaphore semaphore;

    private Student student;

    public AssistantQueue(Semaphore semaphore, Student student) {
        this.semaphore = semaphore;
        this.student = student;
    }

    @Override
    public void run() {
//        log.info("ASISTENT: Student sa id: {}, je dosao na red. Timestamp: {}",
//                student.getId(), System.currentTimeMillis());
        PrintService.getInstance().updatePrintListAssistant("ASISTENT: Student sa id: "
                + student.getId() + ", je dosao na red. Timestamp: " + System.currentTimeMillis());

        try {
            semaphore.acquire();

            {
//                log.info("ASISTENT: Student sa id: {}, je usao na propitivanje. Timestamp: {}",
//                        student.getId(), System.currentTimeMillis());
                PrintService.getInstance().updatePrintListAssistant("ASISTENT: Student sa id: "
                        + student.getId() + ", je usao na propitivanje. Timestamp: " + System.currentTimeMillis());
                Thread.sleep(student.getTimeToDefendWork());

                Random random = new Random();
                student.setScore(random.nextInt(10) + 1);
                StudentQueueService.getInstance().updateSum(student);
//                log.info("ASISTENT: Student sa id: {}, je zavrsio sa propitivanjem, ocena: {}. Timepstamp: {}",
//                        student.getId(), student.getScore(), System.currentTimeMillis());
                PrintService.getInstance().updatePrintListAssistant("ASISTENT: Student sa id: "
                        + student.getId() + ", je zavrsio sa propitivanjem, ocena: "
                        + student.getScore() + ". Timepstamp: " + System.currentTimeMillis());
            }

            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

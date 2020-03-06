package raf.webprogramming.homework_i.threads;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import raf.webprogramming.homework_i.model.Student;
import raf.webprogramming.homework_i.service.PrintService;
import raf.webprogramming.homework_i.service.StudentQueueService;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * {@link AssistantQueue} represents a single scoring of a student in the assistant office.
 * It will take one by one student with a guarantee with using a semaphore in form of a mutex.
 */
@Slf4j
public class AssistantQueue implements Runnable {

    private Semaphore semaphore;

    private Student student;

    private long start;

    public AssistantQueue(Semaphore semaphore, Student student, long start) {
        this.semaphore = semaphore;
        this.student = student;
        this.start = start;
    }

    @Override
    public void run() {
//        log.info("ASISTENT: Student sa id: {}, je dosao na red. Timestamp: {}",
//                student.getId(), System.currentTimeMillis());
        PrintService.getInstance().updatePrintListAssistant("ASISTENT: Student sa id: "
                + student.getId() + ", je dosao na red. Timestamp: " + System.currentTimeMillis()
                + " Razlika od starta: " + (System.currentTimeMillis() - start));

        try {
            semaphore.acquire();

            {
//                log.info("ASISTENT: Student sa id: {}, je usao na propitivanje. Timestamp: {}",
//                        student.getId(), System.currentTimeMillis());
                PrintService.getInstance().updatePrintListAssistant("ASISTENT: Student sa id: "
                        + student.getId() + ", je usao na propitivanje. Timestamp: " + System.currentTimeMillis()
                        + ":" + student.getTimeToDefendWork());
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
        } catch (InterruptedException e) {
//            PrintService.getInstance().updatePrintListAssistant("ASISTENT: Student sa id: "
//                    + student.getId() + ", je prekinut. Timestamp: " + System.currentTimeMillis());
            log.error("ASISTENT: Student sa id: {} je prekinut. Timestamp: {}", student.getId(), System.currentTimeMillis());
        }
    }
}

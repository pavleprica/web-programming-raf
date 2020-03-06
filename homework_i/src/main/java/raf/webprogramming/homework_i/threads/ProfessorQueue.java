package raf.webprogramming.homework_i.threads;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import raf.webprogramming.homework_i.model.Student;
import raf.webprogramming.homework_i.service.PrintService;
import raf.webprogramming.homework_i.service.StudentQueueService;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * {@link ProfessorQueue} represents scoring of students in the professor office. The barrier will take two at once
 * but the flag from {@link StudentQueueService} will determine if the next two can come in.
 */
@Slf4j
public class ProfessorQueue implements Runnable {

    private CyclicBarrier barrier;

    private Student student;

    private long start;

    public ProfessorQueue(CyclicBarrier barrier, Student student, long start) {
        this.barrier = barrier;
        this.student = student;
        this.start = start;
    }

    @Override
    public void run() {
//        log.info("PROFESOR: Student sa id: {}, je dosao na red. Timestamp: {}",
//                student.getId(), System.currentTimeMillis());
        PrintService.getInstance().updatePrintListProfessor("PROFESOR: Student sa id: "
                + student.getId() + ", je dosao na red. Timestamp: " + System.currentTimeMillis()
                + " Razlika od starta: " + (System.currentTimeMillis() - start));

        try {
            StudentQueueService.getInstance().updateProfessorStatus();
            barrier.await();

            {
//                log.info("PROFESOR: Student sa id: {}, je usao na propitivanje. Timestamp: {}",
//                        student.getId(), System.currentTimeMillis());
                PrintService.getInstance().updatePrintListProfessor("PROFESOR: Student sa id: "
                        + student.getId() + ", je usao na propitivanje. Timestamp: " + System.currentTimeMillis()
                        + ":" + student.getTimeToDefendWork());
                Thread.sleep(student.getTimeToDefendWork());

                Random random = new Random();
                student.setScore(random.nextInt(11));
                StudentQueueService.getInstance().updateSum(student);

//                log.info("PROFESOR: Student sa id: {}, je zavrsio sa propitivanjem, ocena: {}. Timepstamp: {}",
//                        student.getId(), student.getScore(), System.currentTimeMillis());
                PrintService.getInstance().updatePrintListProfessor("PROFESOR: Student sa id: "
                        + student.getId() + ", je zavrsio sa propitivanjem, ocena: "
                        + student.getScore() + ". Timepstamp: " + System.currentTimeMillis());
                StudentQueueService.getInstance().updateProfessorStatus();
            }
        } catch (InterruptedException e) {
//            PrintService.getInstance().updatePrintListProfessor("PROFESOR: Student sa id: "
//                    + student.getId() + ", je prekinut. Timestamp: " + System.currentTimeMillis());
            log.error("PROFESOR: Student sa id: {} je prekinut. Timestamp: {}", student.getId(), System.currentTimeMillis());
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

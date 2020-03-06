package raf.webprogramming.homework_i.threads;

import lombok.extern.slf4j.Slf4j;
import raf.webprogramming.homework_i.model.Student;
import raf.webprogramming.homework_i.service.PrintService;
import raf.webprogramming.homework_i.service.StudentQueueService;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

@Slf4j
public class ProfessorQueue implements Runnable {

    private CyclicBarrier barrier;

    private Student student;

    public ProfessorQueue(CyclicBarrier barrier, Student student) {
        this.barrier = barrier;
        this.student = student;
    }

    @Override
    public void run() {
//        log.info("PROFESOR: Student sa id: {}, je dosao na red. Timestamp: {}",
//                student.getId(), System.currentTimeMillis());
        PrintService.getInstance().updatePrintListProfessor("PROFESOR: Student sa id: "
                + student.getId() + ", je dosao na red. Timestamp: " + System.currentTimeMillis());

        try {
            StudentQueueService.getInstance().updateProfessorStatus();
            barrier.await();

            {
//                log.info("PROFESOR: Student sa id: {}, je usao na propitivanje. Timestamp: {}",
//                        student.getId(), System.currentTimeMillis());
                PrintService.getInstance().updatePrintListProfessor("PROFESOR: Student sa id: "
                        + student.getId() + ", je usao na propitivanje. Timestamp: " + System.currentTimeMillis());
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

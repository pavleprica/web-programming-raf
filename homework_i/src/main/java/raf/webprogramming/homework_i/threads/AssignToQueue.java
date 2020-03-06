package raf.webprogramming.homework_i.threads;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import raf.webprogramming.homework_i.model.Student;
import raf.webprogramming.homework_i.service.PrintService;
import raf.webprogramming.homework_i.service.StudentQueueService;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * This class, or rather said Thread will be used to separate the students, and 'send' them either to
 * the professor or to the assistant. In other words it will initiate new Threads in form of the
 * professor or assistant thread.
 */
@Slf4j
public class AssignToQueue implements Runnable {

    /*
    CyclicBarrier will be used by the professor to only allow two students at the time.
     */
    private CyclicBarrier cyclicBarrier;

    /*
    Semaphore is used by the assistant to allow only one student at the time. It has a role of a mutex.
     */
    private Semaphore semaphore;

    /*
    In the beginning, students will be separated in a fashion that an even number must be assigned to the professor
    due to the cyclic barrier. One list contains those who will be sent to the professor, other to the assistant.
     */
    private ArrayList<Student> assistantStudents;
    private ArrayList<Student> professorStudents;

    public AssignToQueue() {
        cyclicBarrier = new CyclicBarrier(2);
        semaphore = new Semaphore(1);

        assistantStudents = new ArrayList<>();
        professorStudents = new ArrayList<>();
    }

    @SneakyThrows
    @Override
    public void run() {

        /*
        Here we separate the students. If the initial number of students isn't even we just send the first half
        to one side, and the other to the other side. If it's even, to avoid the 5-5 situation, rather getting the
        6-4 situation we first assign the first student to the assistant and then go one by one to sides.
         */
        boolean direction;
        if(StudentQueueService.getInstance().getStudentQueue().size() % 2 != 0) {
            direction = false;

            while(!StudentQueueService.getInstance().getStudentQueue().isEmpty()) {

                if(direction)
                    professorStudents.add(StudentQueueService.getInstance().getStudentQueue().pop());
                else
                    assistantStudents.add(StudentQueueService.getInstance().getStudentQueue().pop());

                direction = !direction;
            }

        } else {
            direction = false;

            assistantStudents.add(StudentQueueService.getInstance().getStudentQueue().pop());

            while(!StudentQueueService.getInstance().getStudentQueue().isEmpty()) {

                if(direction)
                    professorStudents.add(StudentQueueService.getInstance().getStudentQueue().pop());
                else
                    assistantStudents.add(StudentQueueService.getInstance().getStudentQueue().pop());

                direction = !direction;
            }
        }

        ArrayList<Thread> startedThreads = new ArrayList<>();

        Random random = new Random();

        /*
        Making a random start time from the program start between 5 and 10 seconds.
         */
        long start = System.currentTimeMillis() + random.nextInt(5000) + 5000;
        long stop = start + 5000;

        /*
        In this loop
         */
        do {
            if (System.currentTimeMillis() > start) {
                if (!professorStudents.isEmpty()) {
                    if (start + professorStudents.get(0).getArriveTime() < System.currentTimeMillis()
                            && StudentQueueService.getInstance().isProfessorAvailable()) {
                        Student student = professorStudents.get(0);
                        professorStudents.remove(0);

                        Thread newThread = new Thread(new ProfessorQueue(cyclicBarrier, student, start));
                        startedThreads.add(newThread);
                        newThread.start();
                    }
                }

                if (!assistantStudents.isEmpty()) {
                    if (start + assistantStudents.get(0).getArriveTime() < System.currentTimeMillis()) {
                        Student student = assistantStudents.get(0);
                        assistantStudents.remove(0);

                        Thread newThread = new Thread(new AssistantQueue(semaphore, student, start));
                        startedThreads.add(newThread);
                        newThread.start();
                    }
                }
            }

        } while (System.currentTimeMillis() <= stop);

        startedThreads.forEach(Thread::interrupt);

        PrintService.getInstance().printLists();

        log.info("Prosek ocena: {}. Broj studenata koji su stigli: {}. Timestamp: {}",
                StudentQueueService.getInstance().getCurrentAvg(),
                StudentQueueService.getInstance().getStudentsCountedInAvg(),
                System.currentTimeMillis());

        log.error("Studenti koji nisu stigli na ocenjivanje:");
        professorStudents.forEach(student -> {
            log.error("Student id: {}", student.getId());
        });
        assistantStudents.forEach(student -> {
            log.error("Student id: {}", student.getId());
        });

        log.info("Start: {} - Stop: {} - Difference: {}", start, stop, (System.currentTimeMillis() - start));

        }



}

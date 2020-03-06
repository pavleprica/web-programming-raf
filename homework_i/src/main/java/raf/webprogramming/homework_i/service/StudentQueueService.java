package raf.webprogramming.homework_i.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import raf.webprogramming.homework_i.model.Student;
import raf.webprogramming.homework_i.threads.AssistantQueue;
import raf.webprogramming.homework_i.threads.ProfessorQueue;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * This service will be used to initialize a certain amount of students to be used by
 * {@link ProfessorQueue} or {@link AssistantQueue}
 * It will also count the average score and the number of students that made it to defend their work.
 */
@Slf4j
public class StudentQueueService {

    private static StudentQueueService instance = null;

    private int studentNumber;

    private float scoreSum = 0;

    @Getter
    private float currentAvg;

    @Getter
    private int studentsCountedInAvg = 0;

    @Getter @Setter
    private boolean isProfessorAvailable = true;
    private int professorCounter = 0;

    @Getter
    private Stack<Student> studentQueue;

    public StudentQueueService() {


    }

    private void init(long studentCreationNumber) {
        studentNumber = (int) studentCreationNumber;
        studentQueue = new Stack<>();
        Random random = new Random();

        /*
        Here the idea is to create students and sort them in that way when you pop prom the stack you get the student
        with the smallest arrive time.
         */
        Student[] students = new Student[studentNumber];

        for(int i = 0; i < studentCreationNumber; i++) {
            students[i] = Student.builder()
                    .id((long) i + 1)
                    .name(RandomStringUtils.randomAlphabetic(10))
                    .timeToDefendWork(random.nextInt(500) + 500)
                    .arriveTime(random.nextInt(1000) + 1)
                    .build();
        }

        Arrays.sort(students);

        for(int i = 0; i < studentCreationNumber; i++) {
            studentQueue.push(students[i]);
        }
    }

    public static void getInstance(long studentCreationNumber) {
        instance = new StudentQueueService();
        instance.init(studentCreationNumber);
    }

    public static StudentQueueService getInstance() {
        return instance;
    }

    public void updateSum(Student student) {
        synchronized (this) {
            scoreSum += student.getScore();
            currentAvg = scoreSum / (float) studentNumber;
            studentsCountedInAvg++;
        }
    }

    /**
     * This is being used so we can use a flag if the professor is available. In terms that
     * if two students are defending their work, other two can't come in at the same time.
     */
    public void updateProfessorStatus() {
        synchronized (this) {
            professorCounter++;
            if(professorCounter == 2) {
                isProfessorAvailable = !isProfessorAvailable;
                professorCounter = 0;
//                log.warn("STATUS PROFESORA: {}", isProfessorAvailable);
                PrintService.getInstance().updatePrintListProfessor("STATUS PROFESORA: " + isProfessorAvailable);
            }
        }
    }

}

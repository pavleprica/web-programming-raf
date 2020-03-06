package raf.webprogramming.homework_i.model;

import lombok.*;
import raf.webprogramming.homework_i.threads.AssistantQueue;
import raf.webprogramming.homework_i.threads.ProfessorQueue;


/**
 * Student represents a single entity that will enter the queue for {@link ProfessorQueue}
 * or {@link AssistantQueue}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Student implements Comparable<Student>{

    /**
     * An id to make a easy difference between students.
     */
    @NonNull
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Name that will be randomly assigned, at this moment, unnecessary
     */
    @NonNull
    private String name;

    /**
     * Time needed by the Student to 'defend' work. In other words, how much will the Thread sleep.
     */
    @NonNull
    private int timeToDefendWork;

    /**
     * Arrive time of the Student to be available to 'defend' his work.
     */
    @NonNull
    private int arriveTime;

    /**
     * His score after 'defending'. Will be randomly assigned.
     */
    private int score;


    /**
     * Used to sort students in a way that the Student that arrives last will go first. That way when
     * pushed to the Stack, the one with the smallest arrive time will go first.
     * @param student with whom to compare
     * @return {@link Integer} depending on the arrive time, this will return -1, 0, 1.
     */
    @Override
    public int compareTo(Student student) {
        return Integer.compare(student.getArriveTime(), this.getArriveTime());
    }
}

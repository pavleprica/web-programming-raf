package rs.webprogramming.homework_iv.main.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assistant {

    private String name;

    private int score;

    private float averageScore;

}

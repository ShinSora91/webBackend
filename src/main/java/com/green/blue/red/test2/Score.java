package com.green.blue.red.test2;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Score {

    private int korea;
    private int math;
    private int eng;
    private float total;
    private float avg;
    private String grade;
    private String name;

    public String calcGrade(float avg){
        String grade = "";
        if(avg >= 90) grade = "수";
        else if(avg >= 80) grade = "우";
        else if(avg >= 70) grade = "미";
        else if(avg >= 60) grade = "양";
        else  grade = "가";
        return grade;
    }

}

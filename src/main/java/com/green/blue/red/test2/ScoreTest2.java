package com.green.blue.red.test2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ScoreTest2 {

    public static void main(String[] args) {
        Map<String, Map<String, Score>> result = new HashMap<>();

        String [][] names = {
                {"홍길동","김길동","김말자"},
                {"권기현","전별","김유라"},
                {"조형우","신소라","임병국"}
        };
        int [][] korea = {
                {30,50,70},
                {100,66,77},
                {55,100,20}
        };
        int [][] math = {
                {70,99,66},
                {70,95,88},
                {66,40,90}
        };
        int [][] eng = {
                {50,98,88},
                {80,55,100},
                {77,50,10}
        };

        for(int i=0; i<3; i++) {
            Map<String, Score> interMap = new HashMap<>();
            for(int j=0; j<3; j++) {
                Score a = new Score();
                a.setName(names[i][j]);
                a.setKorea(korea[i][j]);
                a.setMath(math[i][j]);
                a.setEng(eng[i][j]);
                a.setTotal(a.getKorea() + a.getMath() + a.getEng());
                a.setAvg(a.getTotal()/3.0f);
                a.setGrade(a.calcGrade(a.getAvg()));
                interMap.put((j+1)+"번", a);
                log.info("국어 => {} ", a.getKorea());

                Score a2 = new Score();

            }
            result.put((i+1)+"반", interMap);
        }
        log.info("결과: {}", result);
    }
}

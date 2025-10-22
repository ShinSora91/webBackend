package com.green.blue.red.test2;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class ScoreTest {

    public static void main(String[] args) {
        Map<String, Map<String, Score>> outMap = new LinkedHashMap<>();
//names[0] : {"홍길동","김길동","김말자"}
//names[1] : {"권기현","전별","김유라"}
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


        for(int i=0; i<3; i++){
            Map<String, Score> innerMap = outMap.computeIfAbsent((i + 1) + "반", (key) -> {
                return new LinkedHashMap<>();
            });

            for(int j=0; j<3; j++){
                Score s1 = new Score();
                s1.setName(names[i][j]); //i:0, 0,0 ,홍길동 - 1번학생, i:1, 0,1, 김길동 - 1반에 2번학생
                s1.setKorea(korea[i][j]);
                s1.setMath(math[i][j]);
                s1.setEng(eng[i][j]);
                s1.setTotal(s1.getKorea()+s1.getEng()+ s1.getMath());
                s1.setAvg(s1.getTotal()/3.0f);
                s1.setGrade(s1.calcGrade(s1.getAvg()));
                log.info("1반 => korea:{}, eng:{}, math:{}, total:{}, avg:{}, grade:{}",
                        s1.getKorea(), s1.getEng(), s1.getMath(), s1.getTotal(), s1.getAvg(), s1.getGrade());
                innerMap.put((j+1)+"번", s1);
            }


//            Score s2 = new Score();
//            s2.setName(names[i][1]);
//            s2.setKorea(korea[i][1]);
//            s2.setMath(math[i][1]);
//            s2.setEng(eng[i][1]);
//            s2.setTotal(s2.getKorea()+s2.getEng()+s2.getMath());
//            s2.setAvg(s2.getTotal()/3.0f);
//            s2.setGrade(s2.calcGrade(s2.getAvg()));
//            log.info("2반 => korea:{}, eng:{}, math:{}, total:{}, avg:{}, grade:{}",
//                    s2.getKorea(), s2.getEng(), s2.getMath(), s2.getTotal(), s2.getAvg(), s2.getGrade());
//            innerMap.put("2번", s2);
//
//            Score s3 = new Score();
//            s3.setName(names[i][2]);
//            s3.setKorea(korea[i][2]);
//            s3.setMath(math[i][2]);
//            s3.setEng(eng[i][2]);
//            s3.setTotal(s3.getKorea()+s3.getEng()+s3.getMath());
//            s3.setAvg(s3.getTotal()/3.0f);
//            s3.setGrade(s3.calcGrade(s3.getAvg()));
//            log.info("3반 => korea:{}, eng:{}, math:{}, total:{}, avg:{}, grade:{}",
//                    s3.getKorea(), s3.getEng(), s3.getMath(), s3.getTotal(), s3.getAvg(), s3.getGrade());
//            innerMap.put("3번", s3);


            outMap.put((i+1)+"반", innerMap);

        }

        log.info("{}", outMap);

    }

}

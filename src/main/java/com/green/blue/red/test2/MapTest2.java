package com.green.blue.red.test2;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class MapTest2 {

    public static void main(String[] args) {
        Map<String, Score> map = new LinkedHashMap<>();

//        map.put("a1", new Score());
//        map.get("a1").setName("신소라");
//
//        map.put("b1", new Score());
//        map.get("b1").setName("홍길동");
//
//        map.put("c1", new Score());
//        map.get("c1").setName("아무개");

//        String [] names = {"신소라","홍길동","아무개"};
//        String [] key = {"a1","b1","c1"};

        String [][] keyNames = {
                {"a1","b1","c1"},
                {"신소라","홍길동","아무개"}
        };
//        for(int i=0; i<3; i++){
//            Score s = new Score();
//            s.setName(names[i]);
//            map.put(key[i], s);
//        }
//        log.info("{}", map);

        for(int i=0; i<3; i++){
            Score s = new Score();
            s.setName(keyNames[1][i]);
            map.put(keyNames[0][i], s);
        }
        log.info("{}",map);
    }
}

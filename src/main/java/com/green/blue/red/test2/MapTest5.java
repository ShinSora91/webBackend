package com.green.blue.red.test2;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MapTest5 {

    public static void main(String[] args) {
        Map<Integer, Long> map = new HashMap<>();

        int a = 7;
//        map.put(1,5l);
//        a += 3;
//        map.put(2,10l);
//        a += 5;
//        map.put(3,15l);
//        a += 7;
//        map.put(4,20l);
//        a += 9;
//        log.info("a:{} map:{}",a,map);

        for(int i=0; i<4; i++){
            map.put(i+1, (i+1)*5l);
            a += (i+1)+(i+2);
        }
        log.info("a:{} map:{}", a, map);
    }
}

package com.green.blue.red.test2;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class MapTest1 {

    public static void main(String[] args) {
        String [] str = {"1","2","3","4","5","6"};

        Map<String, Integer> map = new LinkedHashMap<>();

//        map.put("a1", Integer.parseInt(str[0]));
//        map.put("a3", Integer.parseInt(str[1]));
//        map.put("a5", Integer.parseInt(str[2]));
//        map.put("a7", Integer.parseInt(str[3]));
//        map.put("a9", Integer.parseInt(str[4]));
//        map.put("a11", Integer.parseInt(str[5]));
        //0+1, 1+2, 2+3
        //0+1, 0+3, 0+5

        for(int i=0; i<str.length; i++){
            map.put("a"+(i+(i+1)), Integer.parseInt(str[i]));
            //(i*2)+1
        }
        log.info("{}", map);
    }
}

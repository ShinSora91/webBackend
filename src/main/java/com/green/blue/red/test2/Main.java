package com.green.blue.red.test2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Main {

    public static void main(String[] args) {
        List<Map<String,Integer>> listOfMap = new ArrayList<>();
        //[
        //{"A":3, "B":5, "C":7, "D":9}, 1+2, 2+3, 3+4, 4+5
        //{"A":13, "B":15, "C":17, "D":19}, 10+3, 10+5, 10+7, 10+9
        //{"A":23, "B":25, "C":27, "D":29}, 20+3, 20+5, 20+7, 20+9
        //{"A":50, "B":100, "C":150, "D":200}, 5*10, 5*20, 5*30, 5*40
        //]
        String [] str = {"A","B","C","D"};

        Map<String,Integer> map = new HashMap<>();
        Map<String,Integer> map2 = new HashMap<>();
        Map<String,Integer> map3 = new HashMap<>();
        Map<String,Integer> map4 = new HashMap<>();
        for(int i=0; i<4; i++){
            map.put(str[i], (i+1)+(i+2));
            map2.put(str[i], 10+((i+1)+(i+2)));
            map3.put(str[i], 20+((i+1)+(i+2)));
            map4.put(str[i], 5*((i+1)*10));
        }
        listOfMap.add(map);
        listOfMap.add(map2);
        listOfMap.add(map3);
        listOfMap.add(map4);
        log.info("listOfMap : {}", listOfMap);
    }
}

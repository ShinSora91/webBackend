package com.green.blue.red.test2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class Main2 {
    public static Map<String, Integer> createMap(String [] keys, Function<Integer, Integer> num){
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], num.apply(i));
        }
        return map;
    }

    public static void main(String[] args) {
        List<Map<String,Integer>> listOfMap = new ArrayList<>();
        //[
        //{"A":3, "B":5, "C":7, "D":9},
        //{"A":13, "B":15, "C":17, "D":19}, 10+3, 10+5, 10+7, 10+9
        //{"A":23, "B":25, "C":27, "D":29},
        //{"A":50, "B":100, "C":150, "D":200}, 5*10, 5*20, 5*30,5*40
        //]
        String [] str = {"A","B","C","D"};

        listOfMap.add(createMap(str, i -> (i+1)+(i+2)));
        listOfMap.add(createMap(str, i -> 10+((i+1)+(i+2))));
        listOfMap.add(createMap(str, i -> 20+((i+1)+(i+2))));
        listOfMap.add(createMap(str, i -> 5*((i+1)*10)));

        log.info("listOfMap:{}", listOfMap);
    }
}

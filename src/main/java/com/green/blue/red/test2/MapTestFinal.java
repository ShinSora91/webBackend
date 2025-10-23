package com.green.blue.red.test2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MapTestFinal {

    public static void main(String[] args) {
        Map<String, List<Integer>> map = new HashMap<>();
//        List<Integer> list1 = new ArrayList<>();
//        list1.add(1);
//        map.put("a", list1);
//
//        List<Integer> list2 = new ArrayList<>();
//        list2.add(3);
//        map.put("b", list2);
//
//        List<Integer> list3 = new ArrayList<>();
//        list3.add(5);
//        map.put("c", list3); //반복문 list로 하나만 하면 됩니다.
//        log.info("{}",map);

        //문2) {a=[1,2,3,4,5], b={2,4,6,8,10], c={5,10,15,30,25]}

        String [] key = {"a","b","c"};
        for(int i=0; i<3; i++){
            List<Integer> list = new ArrayList<>();
            list.add((i*2)+1);
            map.put(key[i], list);
        }
        log.info("{}", map);

        Map<String, List<Integer>> map2 = new HashMap<>();

        map2.put("a",new ArrayList<>());
        map2.put("b",new ArrayList<>());
        map2.put("c",new ArrayList<>());
        int [] arr = {5,10,15,30,25};
        List<Integer> numList1 = new ArrayList<>();
        List<Integer> numList2 = new ArrayList<>();
        List<Integer> numList3 = new ArrayList<>();
        for(int i=0; i<5; i++){

            numList1.add(i+1);
            numList2.add((i+1)*2);
            numList3.add(arr[i]);
            for(int j=0; j<3; j++){
                map2.put(key[j], numList1);
                map2.put(key[j], numList2);
                map2.put(key[j], numList3);
//                map2.get(key[j]).add(i+1);
//                map2.get(key[j]).add((i+1)*2);
//                map2.get(key[j]).add(arr[i]);
            }

        }
        log.info("{}", map2);
    }
}

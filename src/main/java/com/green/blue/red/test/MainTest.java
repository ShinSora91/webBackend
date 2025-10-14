package com.green.blue.red.test;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.IntStream;

@Slf4j
public class MainTest {

    public static void main(String[] args) {
        Map<String,List<Integer>> map = new HashMap<>();
        List<String> list = Arrays.asList("a","b","c","d","e");
        List<String> listNum = new ArrayList<>();
        List<String> list10 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        List<String> list8 = new ArrayList<>();
//        for(int i=0; i<5; i++){
//            listNum.add(String.valueOf(i+1));
//            map.put("a",listNum);
//        }
//        for(int i=0; i<5; i++){
//            list10.add(String.valueOf((i+1)*10));
//            map.put("b",list10);
//        }
//        for(int i=0; i<5; i++){
//            list3.add(String.valueOf((i+1)*3));
//            map.put("c",list3);
//        }
//        for(int i=0; i<5; i++){
//            list4.add(String.valueOf((i+1)*4));
//            map.put("d",list4);
//        }
//        for(int i=0; i<5; i++){
//            list8.add(String.valueOf((i+1)*8));
//            map.put("e",list8);
//        }
        list.forEach(i -> {
             map.computeIfAbsent(i, j -> new ArrayList<>());
        });

//        for(int i=0; i<5; i++){
//            map.get("a").add(String.valueOf(i+1));
//            map.get("b").add(String.valueOf((i+1)*10));
//            map.get("c").add(String.valueOf((i+1)*3));
//            map.get("d").add(String.valueOf((i+1)*4));
//            map.get("e").add(String.valueOf((i+1)*8));
//        }

        IntStream.rangeClosed(1,5).boxed().forEach(i -> {
            map.get("a").add(i);
            map.get("b").add(i*10);
            map.get("c").add(i * 3);
            map.get("d").add(i * 4);
            map.get("e").add(i * 8);
        });
        List<Integer> list1 = IntStream.rangeClosed(1, 5).map(i -> i * 10).boxed().toList();
        System.out.println(list1);


        System.out.println(map);

//        List<Integer> numbers = Arrays.asList(1,10,3,4,8);
//        Map<String,Integer> mapList = new HashMap<>();
//        for (int i=0; i<list.size(); i++){
//            mapList.put(list.get(i), numbers.get(i));
//        }
//        List<Integer> data = Arrays.asList(1,2,3,4,5);
//        data.forEach( k -> {
//            list.forEach((i) -> {
//                map.computeIfAbsent(i, j -> new ArrayList<>());
//                map.get(i).add(k * mapList.get(i)+"");
//            });
//        });
//        System.out.println(map);
    }
}

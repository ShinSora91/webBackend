package com.green.blue.red.test;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

@Slf4j
public class MainTest2 {

    public static void main(String[] args) {
        Map<String,List<Predicate<Integer>>> map = new HashMap<>();
        //randData 20개를 5개의 Predicate로 검사하면서
        //3의 배수, 5의 배수, 7의 배수, 3보다 큰수인가, 20보다 작은 수인가를 체크
        //{"참":[],"거짓":[]}
        List<Predicate<Integer>> funcList = Arrays.asList(
                (i) -> i%3==0,
                (i) -> i%5==0,
                (i) -> i%7==0,
                (i) -> i>3,
                (i) -> i<20
        );
//        Supplier<String> s1 = () -> "사랑";
//        log.info("{}",s1.get());
//        Consumer<Float> c1 = (v) -> {
//            log.info("{}",v+3);
//        };
//        c1.accept(3.4f);
//        List<Integer> randData = new ArrayList<>();
//        for(int i=0; i<20; i++){
//            randData.add((int)(Math.random()*100));
//        }
//        log.info("{}", randData);

        Map<String,List<Boolean>> resultMap = new HashMap<>();

        List<Integer> randData = new ArrayList<>();
        for(int i=0; i<20; i++){
            randData.add((int)(Math.random()*100));
        }
        log.info("{}", randData);

        List<Boolean> booleanListT = new ArrayList<>();
        List<Boolean> booleanListF = new ArrayList<>();
        for(Predicate<Integer> i : funcList){
            for(int j : randData){
                i.test(j);
                System.out.println(i.test(j));
                if(i.test(j) == true){
                    booleanListT.add(i.test(j));
                } else {
                    booleanListF.add((i.test(j)));
                }
            }
        }
        resultMap.put("참", booleanListT);
        resultMap.put("거짓", booleanListF);
        System.out.println(resultMap);
//
//        funcList.forEach(i -> {
//            randData.forEach(j -> {
//                resultMap.computeIfAbsent("참", v -> new ArrayList<Boolean>());
//                resultMap.computeIfAbsent("거짓", v -> new ArrayList<>(Boolean));
//                resultMap.get("참").add(i.test(j));
//                resultMap.get("거짓").add(i.test(j));
//            });
//        });
//        log.info("최종결과=>{}", resultMap);
    }
}

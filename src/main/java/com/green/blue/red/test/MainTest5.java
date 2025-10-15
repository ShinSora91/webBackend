package com.green.blue.red.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@Slf4j
public class MainTest5 {
    private static String generateGu(List<String> dongStr, int repeat){
        String gu = "";
        for(int i=0; i<repeat; i++) gu += dongStr.get((int)(Math.random()*dongStr.size()));
        return gu;
    }
    public static void main(String[] args) {
        String[][] AddressArr = new String[4][30];
        String [] data = {"도시","구","동","홍길동"};
        List<String> dongStr = Arrays.asList("a","b","c","d","e","f");
        for(int j=0; j<4; j++){
            for(int i=0; i<AddressArr[j].length; i++){
                String u = dongStr.get((int)(Math.random()*dongStr.size()));
                int rr = (int)(Math.random()*20+1);
                AddressArr[j][i] = data[j] + ">>" + generateGu(dongStr, rr);
            }
        }
        for(String[] i :AddressArr){
            for(String j : i){
                log.info("{}", j);
            }
        }
        log.info("====================");
    }
}

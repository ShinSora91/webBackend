package com.green.blue.red.test2;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Main3 {

    public static void main(String[] args) {
        String [] data = {"홍길동3", "김말자9", "김개똥이다8", "권기현이다아6", "지렁이3"};
//        {"문자열결합":"홍길동김말자김개똥이다권기현이다아지렁이", "더하기":3+9+8+6+3}

        String test = data[data.length-1].substring(data.length-2);
        log.info("테스트:{}",test);


        Map<String, String> map = new HashMap<>();

        int num = 0;
        String strCombination = "";
        String strNumCombination = "";
        for(int i=0; i<data.length; i++){
            strCombination += data[i].substring(0, data[i].length()-1);
            strNumCombination = data[i].substring(data[i].length()-1);
            num += Integer.parseInt(strNumCombination);
        }
        map.put("문자열결합", strCombination);
        map.put("더하기", ""+num);

        log.info("answer : {}",map);

    }
}

package com.green.blue.red.test2;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Main5 {

    public static int numSum(String[] str, int idx){
        return Integer.parseInt(str[idx].substring(str[idx].length()-1));
    }

    public static String strSum(String[] str, int idx){
        String s = "";
        for(int i=0; i<str.length; i++){
            s += str[idx].substring(0,str[idx].length()-1);
        }

        return s;
    }

    public static void main(String[] args) {

        String[][] str = {
                {"사랑5","믿음7"},
                {"증오9","필승3"},
                {"별2","기현8"},
        };
//        {"a_1" : "사랑증오별"}
//        {"a_2" : "믿음필승기현"}
//        {"a_3" : 5+9+2}
//        {"a_4" : 7+3+8}
        Map<String, String> map = new HashMap<>();

        String stra1 = "";
        String stra2 = "";
        int num1 = 0;
        int num2 = 0;
        for(int i=0; i<str.length; i++) {
            stra1 += str[i][0].substring(0,str[i][0].length()-1);
            stra2 += str[i][1].substring(0,str[i][1].length()-1);
            num1 += Integer.parseInt(str[i][0].substring(str[i][0].length()-1));
            num2 += Integer.parseInt(str[i][1].substring(str[i][1].length()-1));
        }
        map.put("a_1", stra1);
        map.put("a_2", stra2);
        map.put("a_3", ""+num1);
        map.put("a_4", ""+num2);
        log.info("map:{}", map);

        //---------------------------------------------

        Map<String, Integer> map2 = new HashMap<>();
        for(int i=0; i<2; i++) map2.put("a_"+(i+1),0);
        for(int i=0; i<3; i++) {
            for(int j=0; j<2; j++){
                String key = "a_"+(j+1);
                map2.put(key, map2.get(key) + numSum(str[i],j));
            }
        }
        log.info("map2:{}",map2);

        Map<String, String> map3 = new HashMap<>();

        String st = "";
        for(int i=0; i<2; i++) map3.put("a_"+(i+1), "");
        for(int i=0; i<3; i++) {
            for(int j=0; j<2; j++){
                String key = "a_"+(j+1);
                map3.put(key, map.get(key)+strSum(str[i], j));
            }
        }
        log.info("map3:{}",map3);
    }
}

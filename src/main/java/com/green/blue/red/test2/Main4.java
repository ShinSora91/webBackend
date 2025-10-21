package com.green.blue.red.test2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class Main4 {
    public static int resultVal (String[] str, int idx) {
        return Integer.parseInt(str[idx]);
    }

    public static void main(String[] args) {
        String[][] str = {
                {""+11,"23","197","210"},
                {""+21,"33","117","210"},
                {""+19,"73","127","212"},
                {""+14,"63","172","213"},
                {""+111,"13","117","214"},
                {""+1,"3","5","7"},
        };
//        {"a_1" : 11+21+19+14+111+1, "a_2":23+33+73+63+13+3}

        Map<String, Integer> map = new HashMap<>();
        int sumA_0 = 0;
        int sumA_1 = 0;
        int sumA_2 = 0;
        int sumA_3 = 0;

        for (int i = 0; i < str.length; i++) {
            sumA_0 += Integer.parseInt(str[i][0]);
            sumA_1 += Integer.parseInt(str[i][1]);
            sumA_2 += Integer.parseInt(str[i][2]);
            sumA_3 += Integer.parseInt(str[i][3]);
        }
        map.put("a_0", sumA_0);
        map.put("a_1", sumA_1);
        map.put("a_2", sumA_2);
        map.put("a_3", sumA_3);

        log.info("map : {}", map);

        Map<String, Integer> map2 = new HashMap<>();
        for(int i=0; i<4; i++) map2.put("a_" + (i+1),0);
        for(int i=0; i<6; i++) {
            for(int j=0; j<str[0].length; j++) {
                String key = "a_"+(j+1);
                log.info("key:{}", key);
                map2.put(key, map2.get(key) + resultVal(str[i], j));
            }
        }
        log.info("map2 = {}", map2);
    }
}

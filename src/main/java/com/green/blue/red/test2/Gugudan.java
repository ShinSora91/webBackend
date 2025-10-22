package com.green.blue.red.test2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Gugudan {
    public static String gugu(int a){
        String re = "";
        for(int i=1; i<10; i++){
            re += a+"x"+i+"="+(a*i)+"\n";
        }
        return re;
    }

    public static void main(String[] args) {

        String v = gugu(4);
        System.out.println("============");
        System.out.println(v);

    }
}

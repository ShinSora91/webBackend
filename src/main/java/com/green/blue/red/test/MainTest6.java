package com.green.blue.red.test;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class MainTest6 {
    private static void generateGu(List<String> dongStr, int [] u, AddressDTO d){
        dongStr.add(1,"자랑");
        u[1]=100;
        d.setGu("여기는 generateGu 함수의 구에서 변경했어요");
    }
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("사랑","믿음","증오");
        List<String> list = new ArrayList<>();
        String [] str = {"사랑","믿음","증오"};
        for(int i=0; i<str.length; i++){
            list.add(str[i]);
        }
        int [] arr = new int[]{1,2,3};
        AddressDTO r = new AddressDTO();
        r.setGu("여기는 main 함수에서 호출할때에요");
        generateGu(list,arr,r);
        list.forEach(i -> log.info("{}", i));
        System.out.println("==========================");
        for(int i : arr) log.info("{}", i);
        log.info("addressDTO:{}", r);

        List<Integer> intList = Arrays.asList(1,2,3,4,5);
        for(int i=0; i<intList.size(); i++){
//            log.info("test:{}",(int)(Math.random()*5));
            log.info("test2:{}",(int)(Math.random()*intList.size()));
        }
        System.out.println(intList.size());
    }
}

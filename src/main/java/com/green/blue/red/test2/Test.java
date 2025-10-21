package com.green.blue.red.test2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class Test {

    public static void main(String[] args) {
        List<Star> starList = new ArrayList<>();
        for(int i=0; i<10; i++){
            Optional.ofNullable(null).orElseGet(() -> {
                //Optional.of : null을 허용하지 않는다.
                return starList.add(Star.builder()
                        .no((int)(Math.random()*10))
                        .math((int)(Math.random()*100))
                        .eng((int)(Math.random()*100))
                        .build());
            });
        }
        for(Star s : starList){
            log.info("no: {}, math: {}, eng: {}", s.getNo(), s.getMath(), s.getEng());
        }

    }
}

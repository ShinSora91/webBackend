package com.green.blue.red.test2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapTest3 {

    public static void main(String[] args) {
        int a = 0;

        a+=3;
        log.info("a:{}", a);

        a+=3;
        log.info("a:{}", a);

        a+=3;
        log.info("a:{}", a);
        log.info("====================");

        a=0;
        a+=3;
        a+=3;
        a+=3;
        log.info("a:{}",a);
        log.info("a:{}",a);
        log.info("a:{}",a);
    }
}

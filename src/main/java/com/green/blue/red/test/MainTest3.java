package com.green.blue.red.test;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Predicate;

@Slf4j
public class MainTest3 {

    public static void main(String[] args) {
        Map<String,List<Predicate<AddressDTO>>> map = new HashMap<>();

        //1) Predicate 10개 임의로 만드세요

        //2) random 데이터 30개를 저장, 10개의 Predicate를 하나 꺼내어 30개의 random 데이터에 적용하고

        //{"city":[], "gu":[], "age":[]}
        AddressDTO dto = AddressDTO.builder()
                .ano(4L)
                .dong("방학동")
                .city("대한민국")
                .age(13)
                .gu("도봉구")
                .build();
        Predicate<AddressDTO> p1 = i -> i.getDong().length() > 3;

        List<Predicate<AddressDTO>> prdicateList = Arrays.asList(
                i -> i.getCity() == "성남",
                i -> i.getCity() == "광주",
                i -> i.getCity() == "서울",
                i -> i.getCity() == "부산",
                i -> i.getGu().length() > 3,
                i -> i.getGu().length() > 2,
                i -> i.getGu().length()%2==0,
                i -> i.getAge() >= 10,
                i -> i.getAge() <= 20,
                i -> i.getAge() >= 30
        );
    }
}

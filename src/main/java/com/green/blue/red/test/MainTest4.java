package com.green.blue.red.test;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Predicate;

@Slf4j
public class MainTest4 {
    private static String gugu(String s, int i){
        String merge="";
        for (int j = 0; j <i ; j++) {
            merge+=s;
        }
        return  merge;
    }
    private static String generateGu(List<String> dongStr, int repeat) {
        String gu = "";
        for (int i=0; i<repeat; i++)  gu+=dongStr.get((int)(Math.random()* dongStr.size()));
        return gu;
    }
    public static void main(String[] args) {
        String rrr="ab,CD,zzF";
        String[][] AddressArr= new String[4][30];
        String [] data ={"도시","구","동","이재오"};
        List<String> dongStr = Arrays.asList("a","b","c","d","e","f");
        for (int j= 0; j < 4;j++) {
            for (int i = 0; i <AddressArr[j].length ; i++) {
                int rr= (int)(Math.random()*20+1);
                AddressArr[j][i] =data[j]+">>" +generateGu(dongStr ,rr);
            }
        }
        for(String[] i :AddressArr) {
            for(String j : i)
                log.info("{}",j);
        }
        log.info("==================");
        //AddressDTO 3개를 저장
        List<AddressDTO> dtoList=new ArrayList<>();
        for (int i = 0; i < AddressArr[0].length; i++) {
            dtoList.add( AddressDTO.builder()
                    .ano((long)(i+1))
                    .name(AddressArr[3][i])
                    .city(AddressArr[0][i])
                    .gu(AddressArr[1][i])
                    .dong(AddressArr[2][i])
                    .age((i+1)*7)
                    .build());
        }

        //1) Predicate 10는 임의로 만드세요
        List<Predicate<AddressDTO>>  fuList= Arrays.asList(
                (i) -> i.getDong().length()>3,
                (i)->gugu(i.getGu(), i.getGu().length()).startsWith("도"),
                i->(i.getDong()+i.getGu()+i.getCity()).trim().toLowerCase().split(",")[0].indexOf("f")==3,
                i->i.getAge()>i.getAno(),
                i->i.getAge()<=i.getAno(),
                i->i.getAge()==i.getAno(),
                i->i.getName().length()>i.getAno(),
                i->i.getName().length()>i.getAge(),
                i->i.getCity().equals(i.getGu()),
                i->!i.getCity().equals(i.getGu())
        );
        Map<String, List<Predicate<AddressDTO>>> map=new HashMap<>();
        map.put("fn",fuList);
        //2) AddressDTO객체를 10개를 생성하여 DATA를 채운후 적용하세요
//        List<ResultDTO> resultDTOS= new ArrayList<>();
        Map<String,List<ResultDTO>> finalResultMap =new HashMap<>();
        map.get("fn").forEach(i->{
            dtoList.forEach(j->{
                finalResultMap.computeIfAbsent("city", k -> new ArrayList<>());
                finalResultMap.computeIfAbsent("gu", k -> new ArrayList<>());
                finalResultMap.computeIfAbsent("age", k -> new ArrayList<>());
                ResultDTO rr=null;
                if(i.test(j)) {
                    rr = ResultDTO.builder()
                            .cityAndGuDong(j.getCity()+j.getGu()+j.getDong())
                            .anoAnodAge((int)(j.getAno()+j.getAge()))
                            .build();
                    if(rr.getAnoAnodAge()>20) finalResultMap.get("city").add(rr);
                    else  {
                        finalResultMap.get("gu").add(rr);
                    }
                } else{
                    finalResultMap.get("age").add(rr);
                }
            });
        });
        log.info("final=>{}",finalResultMap);
    }
}

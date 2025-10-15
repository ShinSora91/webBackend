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
      List<Predicate<AddressDTO>> prdicateListCity = Arrays.asList(
          i -> "성남".equals(i.getCity()),
          i -> "광주".equals(i.getCity()),
          i -> "서울".equals(i.getCity()),
          i -> "부산".equals(i.getCity())
        );
      List<Predicate<AddressDTO>> prdicateListGu = Arrays.asList(
          i -> i.getGu().length() > 3,
          i -> i.getGu().length() > 2,
          i -> i.getGu().length() % 2==0
      );
      List<Predicate<AddressDTO>> prdicateListAge = Arrays.asList(
          i -> i.getAge() >= 10,
          i -> i.getAge() <= 20,
          i -> i.getAge() >= 30
      );
      String [] city = {"성남","광주","부산","서울","전주","대구","완도"};
      String [] gu = {"중원구","수정구","권선구","기흥구","분당구"};
      List<AddressDTO> addressDTOList = new ArrayList<>();
      for(int i=0; i<30; i++){
          AddressDTO dto = AddressDTO.builder()
                  .city(city[i % city.length])
                  .gu(gu[i % gu.length])
                  .age((int)(Math.random()*50))
                  .build();
          addressDTOList.add(dto);
      }
      Map<String, List<Boolean>> result = new HashMap<>();
      result.put("city", new ArrayList<>());
      result.put("gu", new ArrayList<>());
      result.put("age", new ArrayList<>());
      for(Predicate<AddressDTO> pcity : prdicateListCity){
          for(AddressDTO addDto : addressDTOList){
              result.get("city").add(pcity.test(addDto));
          }
      }
      for(Predicate<AddressDTO> pgu : prdicateListGu){
          for(AddressDTO addDto : addressDTOList){
              result.get("gu").add(pgu.test(addDto));
          }
      }
      for(Predicate<AddressDTO> page : prdicateListGu){
          for(AddressDTO addDto : addressDTOList){
              result.get("age").add(page.test(addDto));
          }
      }
      log.info("city : {}", result);
    }
}

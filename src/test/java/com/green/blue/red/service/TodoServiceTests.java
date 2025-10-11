package com.green.blue.red.service;

import com.green.blue.red.dto.PageRequestDTO;
import com.green.blue.red.dto.PageResponseDTO;
import com.green.blue.red.dto.TodoDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class TodoServiceTests {
    @Autowired
    private TodoService service;

    @Test
    public void testRegister(){
        TodoDTO dto = TodoDTO.builder()
                .title("서비스테스트")
                .writer("신소라 서비스테스트")
                .dueDate(LocalDate.of(2025,10,1))
                .build();
        Long tno = service.register(dto);
        log.info("Tno:{}", tno);
    }
    //문 1) 데이터 100개 추가
    @Test
    public void testRegister2(){
        for(int i=0; i<100; i++) {
            TodoDTO dto = TodoDTO.builder()
                    .title("서비스 제목"+i)
                    .writer("서비스 작성자"+i)
                    .dueDate(LocalDate.of(2025,10,1))
                    .build();
            Long tno = service.register(dto);
            log.info("Tno:{}", tno);
        }
    }

//    @Test
//    public void testList(){
//        Map<String,List<Long>> map = new HashMap<>();
//        List<Long> evenList = service.list().stream().filter(i ->
//                i.getTitle().length() % 2 == 0).map(j->j.getTno()).toList();
//        List<Long> oddList = service.list().stream().filter(i ->
//                i.getTitle().length() % 2 == 0).map(j->j.getTno()).toList();
//
//        map.put("짝수",evenList);
//        map.put("홀수",oddList);
//        System.out.println(map);
//    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .build();
        PageResponseDTO<TodoDTO> response = service.list(pageRequestDTO);
        log.info("res:{}",response);
    }
}

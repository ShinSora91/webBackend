package com.green.blue.red.controller;

import com.green.blue.red.domain.Todo;
import com.green.blue.red.dto.PageRequestDTO;
import com.green.blue.red.dto.PageResponseDTO;
import com.green.blue.red.dto.TodoDTO;
import com.green.blue.red.repository.TodoRepository;
import com.green.blue.red.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService service;

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO){
        return  service.list(pageRequestDTO);
    }

    @GetMapping("/read/{tno}")
    public TodoDTO get(@PathVariable("tno") Long tno){
        log.info("여기는 데이터 하나조회 controller, tno={}", tno);
        return service.get(tno);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody TodoDTO dto){
        log.info("todo controller 추가 : dto=> {}", dto);
        Long tno = service.register(dto);
        return Map.of("TNO",tno);
    }

    @PutMapping("/{tno}")
    public Map<String, String> modify(@PathVariable(name = "tno") Long tno,
                                      @RequestBody TodoDTO dto){
        dto.setTno(tno);
        log.info("1) 수정 컨트롤러 dto:{}", dto);
        service.modify(dto);
        return Map.of("result", "성공");
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> remove(@PathVariable(name = "tno") Long tno){
        log.info("controller 삭제 tno :{}", tno);
        service.remove(tno);
        return Map.of("result","성공");
    }
}

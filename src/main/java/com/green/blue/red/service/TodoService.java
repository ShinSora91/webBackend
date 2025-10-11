package com.green.blue.red.service;

import com.green.blue.red.dto.PageRequestDTO;
import com.green.blue.red.dto.PageResponseDTO;
import com.green.blue.red.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    Long register(TodoDTO dto);
    PageResponseDTO<TodoDTO> list(PageRequestDTO dto);
    TodoDTO get(Long tno);
    int remove(Long tno);
    void modify(TodoDTO dto);
}

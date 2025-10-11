package com.green.blue.red.service;

import com.green.blue.red.domain.Todo;
import com.green.blue.red.dto.PageRequestDTO;
import com.green.blue.red.dto.PageResponseDTO;
import com.green.blue.red.dto.TodoDTO;
import com.green.blue.red.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor //생성자 자동 주입(auto injection)
public class TodoServiceImpl implements TodoService{
    private final ModelMapper mapper;
    private final TodoRepository repository;

    @Override
    public Long register(TodoDTO dto) {
        Todo todo = mapper.map(dto, Todo.class);
        Todo savedTodo = repository.save(todo);
        return savedTodo.getTno();
    }

    @Override
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(), Sort.by("tno").descending());
        Page<Todo> result = repository.findAll(pageable);
        List<TodoDTO> dtoList = result.getContent().stream().map(i->mapper.map(i,TodoDTO.class)).toList();
        Long totalCount = result.getTotalElements();
        PageResponseDTO<TodoDTO> responseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .pageRequestDTO(pageRequestDTO)
                .totalCount(totalCount)
                .build();
        return responseDTO;
    }

    @Override
    public TodoDTO get(Long tno) {
        return mapper.map(repository.findById(tno), TodoDTO.class);
    }

    @Override
    public int remove(Long tno) {
        repository.deleteById(tno);
        return 1;
    }

    @Override
    public void modify(TodoDTO dto) {
        log.info("2) controller 로부터 넘어온 dto적용 : {}", dto);
        //수정하려면 조회를 한다
        Optional<Todo> todoOptional = repository.findById(dto.getTno());
        Todo todo = todoOptional.get(); //db에서 가져와서 (수정하기 전)
//        todo = Todo.builder()
//                .title(dto.getTitle())
//                .dueDate(dto.getDueDate())
//                .writer(dto.getWriter())
//                .tno(dto.getTno())
//                .build(); //dto(브라우저(리엑트로부터 입력된 정보를) entity에 저장하고 db에 저장
//        log.info("3) dto적용 후 entity service: {}", todo2);

        // todo 는 db 에서 꺼낸것, 이거를 JPA 가 기억하고 있어요
        // 그래서 save할 필요없어 JPA가 todo가 변경된것을 감지할 수 있음
        // 그래서 객체 수정하듯이 change 메서드로 수정하면 됨
        todo.changeComplete(dto.isComplete());
        todo.changeTitle(dto.getTitle());
        todo.changeDueDate(dto.getDueDate());
//        repository.save(todo2);//수정후의 데이터 저장
    }


}

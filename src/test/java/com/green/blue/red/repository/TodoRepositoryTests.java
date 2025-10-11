package com.green.blue.red.repository;

import com.green.blue.red.domain.Todo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@SpringBootTest
@Slf4j
public class TodoRepositoryTests {
    @Autowired
    private TodoRepository r;
    boolean[] rr={true,false};
    @Test
    public void insertDummy(){
        //200개 추가
        List<Todo> list = LongStream.rangeClosed(1, 200).mapToObj(i -> new Todo(
                null,
                "제목" + i,
                "작성자" + i,
                rr[(int) (Math.random() * rr.length)],
                LocalDate.of(2020, (int)(1+(1+i)%11), (int) (1+i%27))
        )).toList();
        list.forEach(i->r.save(i));
    }
    @Test
    public void testRead(){
        //문제를 풀어요
        //findAll로 모든id로 검색을 하여 짝수인 id 홀수인 id를 분류하여
        Map<String,List<Todo>> map =new HashMap<>();
        map.put("홀수",new ArrayList<>());
        map.put("짝수",new ArrayList<>());
        //findById로 검색하여
//        {"홀수":[],"짝수":[]}
        r.findAll().forEach(i->{
                    if(i.getTno()%2==0) map.get("짝수").add(i);
                    else map.get("홀수").add(i);
            Optional<Todo> v = r.findById(i.getTno());
            System.out.println("이것은  findById를 공부하기위함 =>"+v.get());
        });
        System.out.println(map);
    }

    @Test
    public void testModify(){
        Long tno = 2l;
        Optional<Todo> result = r.findById(tno);
        Todo todo = result.orElseThrow();
        todo.changeTitle("2번 수정");
        todo.changeComplete(true);
        todo.changeDueDate(LocalDate.of(2025,9,30));
        r.save(todo);
    }
    // 문1) 전체 데이터를 tno값을 title의 문자열을 읽은 값에 앞에 추가하여 저장
    // 예를 들어 "감사합니다" 였으면 3감사합니다로 수정
    // complete는 true면 false로 false면 true로 수정

    @Test
    public void testModify2(){
        List<Todo> list = r.findAll();

//        for(Todo j : list){
//            j.changeTitle(j.getTno()+"제목수정");
//            j.changeComplete(!j.isComplete());
//            r.save(j);
//        }

//        list.stream().map(i -> i.changeTitle("제목수정test")).toList();
        //map은 데이터를 변경해서 return하고
        //changeTitle은 void 반환값이 없다.
        //changeTitle을 반환하려하지만 changeTitle은 반환값이 없기 때문에 실행불가
        list.forEach(i-> {
            i.changeTitle(i.getTno()+"테스트");
            i.changeComplete(!i.isComplete());
            r.save(i);
        });
    }

    @Test
    public void testDelete(){
//        Long tno = 1l;
//        r.deleteById(tno);
        r.findAll().forEach(i -> {
            if(i.getTitle().length() % 2 == 0){
                r.deleteById(i.getTno());
            }
        });
    }

    @Test
    public void testPaging(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("tno").descending());
        Page<Todo> result = r.findAll(pageable);
        System.out.println(result.getTotalElements());
        result.getContent().stream().forEach(i -> log.info("todo={}", i));
    }

    @Test
    public void testPaging2(){
        int dataSize = r.findAll().size();
        int size = 10;
        for (int j :Arrays.asList(10,20,30,40)){
            for(int i=0; i<(int)(dataSize/size)+1; i++){
                Pageable pageable = PageRequest.of(i, size, Sort.by("tno").descending());
                Page<Todo> p = r.findAll(pageable);
                log.info("page={}, data={}", i, p);
            }
            System.out.println(j + "개의  size=====================");
        }
        //[tno, title, writer]을 기준으로 page 단위로 출력,
        //오름차순, 내림차순으로 번갈아 출력
    }

    @Test
    public void testPaging3() {
        int cnt = 0;
        int dataSize = r.findAll().size();
        for(int r1=0; r1<2; r1++){
            for (String k : Arrays.asList("tno", "title", "writer")) {
                for (int j : Arrays.asList(10, 20, 30, 40)) {
                    log.info(cnt+++ "r={}, 정렬기준:{}, size={}", r1, k, j );
                    for (int i=0; i<(int)(dataSize/j); i++) {
                        if(r1%2 == 0){
                            Pageable pageable = PageRequest.of(i,j,Sort.by(k));
                            Page<Todo> p = r.findAll(pageable);
                            log.info("page={}, data={}", i,p);
                        } else {
                            Pageable pageable = PageRequest.of(i,j,Sort.by(k).descending());
                            Page<Todo> p = r.findAll(pageable);
                            log.info("page={}, data={}", i,p);
                        }
                    }
                    System.out.println(j + "개의 size=====================");
                }
            }
        }
    }

}

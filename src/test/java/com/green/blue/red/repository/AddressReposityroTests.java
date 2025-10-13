package com.green.blue.red.repository;

import com.green.blue.red.domain.Address;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Slf4j
public class AddressReposityroTests {

    @Autowired
    private AddressRepository repository;

    @Test
    public void testInsert() {
        for(int i=0; i<100; i++){
            Address add = Address.builder()
                    .city("성남"+i)
                    .gu("중원구"+i)
                    .dong("중앙동"+i)
                    .name("홍길동"+i)
                    .age((int)(Math.random()*10)+20)
                    .build();
            add.addImageString("image1.jpg");
            add.addImageString("image2.jpg");
            repository.save(add);
        }
    }

    @Test
    public void dataget() {
        Pageable p = PageRequest.of(1,10, Sort.by("ano").descending());
        Page<Object[]> result = repository.getList(p);
        result.getContent().forEach(i -> log.info("data=>{}",i));
    }
}

package com.green.blue.red.service;

import com.green.blue.red.dto.PageRequestDTO;
import com.green.blue.red.dto.PageResponseDTO;
import com.green.blue.red.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@Slf4j
public class ProductServiceTests {
    @Autowired
    private ProductService productService;

    @Test
    public void testList(){
        PageRequestDTO dto = PageRequestDTO.builder().build();

        PageResponseDTO<ProductDTO> result = productService.getList(dto);

        result.getDtoList().forEach(i -> log.info("dto={}",i));
    }

    @Test
    public void testRegister(){
        ProductDTO dto = ProductDTO.builder()
                .pname("새로운 상품 서비스")
                .pdesc("신규 추가 상품입니다. 서비스에서")
                .price(100)
                .build();
        dto.setUploadFileNames(
                List.of(UUID.randomUUID()+"_"+"Test1.jpg",
                        UUID.randomUUID()+"_"+"Test2.jpg"));
        productService.register(dto);
    }

    @Test
    public void testRead(){
        ProductDTO productDTO = productService.get(112l);
        log.info("1) {}", productDTO);
        log.info("2) {}", productDTO.getUploadFileNames());
    }
}

package com.green.blue.red.controller;

import com.green.blue.red.dto.PageRequestDTO;
import com.green.blue.red.dto.PageResponseDTO;
import com.green.blue.red.dto.ProductDTO;
import com.green.blue.red.service.ProductService;
import com.green.blue.red.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/product")
public class ProductController {

    private final CustomFileUtil fileUtil;
    private final ProductService service;

    @PostMapping("/")
    public Map<String, Long> register(ProductDTO dto){
        log.info("register:{} ", dto);
        List<MultipartFile> files = dto.getFiles();//Multi
        List<String> uploadFileNames = fileUtil.saveFiles(files);
        dto.setUploadFileNames(uploadFileNames);
        log.info("{}",uploadFileNames);
        Long pno = service.register(dto);
        return Map.of("결과", pno);
    }
    //이미지 보여주기
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName){
        log.info("여기는 이미지 달라고 하는 controller fileName=> {}", fileName);
        return fileUtil.getFile(fileName);
    }
    //Page당 전체 목록 조회
    @GetMapping("/list")
    public PageResponseDTO<ProductDTO> list(PageRequestDTO dto){
        log.info("list controller .......... pageRequest:{}, start={}",dto);
        return service.getList(dto);
    }
}

package com.green.blue.red.controller;

import com.green.blue.red.dto.ProductDTO;
import com.green.blue.red.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/products")
public class ProductController {

    private final CustomFileUtil fileUtil;

    @PostMapping("/")
    public Map<String, String> register(ProductDTO dto){
        log.info("register:{} ", dto);
        List<MultipartFile> files = dto.getFiles();
        List<String> uploadFileNames = fileUtil.saveFiles(files);
        dto.setUploadFileNames(uploadFileNames);
        log.info("{}",uploadFileNames);
        return Map.of("결과", "성공");
    }
    //이미지 보여주기
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName){
        log.info("여기는 이미지 달라고 하는 controller fileName=> {}", fileName);
        return fileUtil.getFile(fileName);
    }
}

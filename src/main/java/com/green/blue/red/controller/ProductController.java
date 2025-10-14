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
@RequestMapping("/api/products")
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
    public ResponseEntity<Resource> viewFileGet(@PathVariable("fileName") String fileName){
        log.info("여기는 이미지 달라고 하는 controller fileName=> {}", fileName);
        return fileUtil.getFile(fileName);
    }
    //Page당 전체 목록 조회
    @GetMapping("/list")
    public PageResponseDTO<ProductDTO> list(PageRequestDTO dto){
        log.info("list controller .......... pageRequest:{}",dto);
        return service.getList(dto);
    }
    @PutMapping("/{pno}")
    public Map<String, String> modify(@PathVariable("pno") Long pno, ProductDTO dto){
        dto.setPno(pno);
        ProductDTO oldProductDTO = service.get(pno);
        //기존의 파일들(데이터 베이스에 존재하는 파일들- 수정과정에서 삭제되었을수있음)
        List<String> oldFileNames = oldProductDTO.getUploadFileNames();
        //새로 업로드 해야 하는 파일들
        List<MultipartFile> files = dto.getFiles();
        //새로 업로드 되어서 만들어진 파일 이름들
        List<String> currentUploadFileNames = fileUtil.saveFiles(files);
        //화면에서 변화없이 계속 유지된 파일들
        List<String> uploadedFileNames = dto.getUploadFileNames();
        //유지되는 파일들 + 새로 업로드된 파일 이름들이 저장해야하는 파일 목록이 됨
        if(currentUploadFileNames != null && currentUploadFileNames.size() > 0) uploadedFileNames.addAll(currentUploadFileNames);
        //수정 작업
        service.modify(dto);
        if(oldFileNames != null && oldFileNames.size() > 0){
            //지워야하는 파일목록 찾기
            //예전 파일들 중에서 지워져야 하는 파일 이름들
            List<String> removeFiles = oldFileNames
                    .stream().filter(i -> uploadedFileNames.indexOf(i) == -1).toList();//검색되지 않으면 -1이 반환된
            //실제 파일 삭제
            fileUtil.deleteFiles(removeFiles);
        }
        return Map.of("RESULT", "SUCCESS");
    }
    @DeleteMapping("/{pno}")
    public Map<String, String> remove(@PathVariable("pno") Long pno){
        //삭제해야 할 파일 알아내기
        List<String> oldFileNames = service.get(pno).getUploadFileNames();
        service.remove(pno);
        fileUtil.deleteFiles(oldFileNames);
        return Map.of("RESULT","SUCCESS");
    }
}

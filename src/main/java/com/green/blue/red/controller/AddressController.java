package com.green.blue.red.controller;

import com.green.blue.red.domain.Address;
import com.green.blue.red.domain.AddressImage;
import com.green.blue.red.dto.AddressDTO;
import com.green.blue.red.dto.AddressImageDTO;
import com.green.blue.red.dto.PageRequestDTO;
import com.green.blue.red.service.AddressService;
import com.green.blue.red.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/address")
public class AddressController {

    private final CustomFileUtil fileUtil;
    private final AddressService service;
    private final ModelMapper mapper;

    @PostMapping("/")
    public Map<String, Long> register(AddressDTO dto){
        List<MultipartFile> files = dto.getFiles();
        List<String> uploadFileNames = fileUtil.saveFiles(files);
        dto.setUploadFileName(uploadFileNames);
        Long ano = service.register(dto);
        return Map.of("결과", ano);
    }

    //별도의 AddressDTO 에 담는데 각각의 address 하나당 image 가 여러개 있다 AddressDTO 를 여러개 반환
    @GetMapping("/list")
    public ResponseEntity<List<AddressDTO>> list(PageRequestDTO dto){
//        log.info("controller list : {} ", dto);
        Page<Object[]> result = service.list(dto);
        log.info("여기는 address controller,{}",result.get());
        List<AddressDTO> list = result.get().map(i -> {
            log.info("2) i:{}", i);
            Address a = (Address) i[0];
            AddressImage ai = (AddressImage) i[1];
            log.info("add image:{}",ai);
            AddressDTO addDTO = mapper.map(a, AddressDTO.class);

            AddressImageDTO addressImageDTO = mapper.map(ai, AddressImageDTO.class);
            addDTO.setUploadFileName(List.of(ai.getFileName()));
            return addDTO;
        }).toList();
        return ResponseEntity.ok(list);
    }
}

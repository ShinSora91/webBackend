package com.green.blue.red.service;

import com.green.blue.red.domain.Address;
import com.green.blue.red.dto.AddressDTO;
import com.green.blue.red.dto.PageRequestDTO;
import com.green.blue.red.repository.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AddressServiceImpl implements AddressService{

    @Autowired
    private final AddressRepository repository;

    private Address addDtoToEntity(AddressDTO dto){
        Address address = Address.builder()
                .ano(dto.getAno())
                .city(dto.getCity())
                .gu(dto.getGu())
                .dong(dto.getDong())
                .name(dto.getName())
                .age(dto.getAge())
                .build();

        List<String> uploadFileName = dto.getUploadFileName();
        if(uploadFileName == null) return address;
        uploadFileName.stream().forEach(i -> {
            address.addImageString(i);
        });
        return address;
    }
    @Override
    public Long register(AddressDTO dto) {
        Address address = addDtoToEntity(dto);
        Address result =  repository.save(address);
        return result.getAno();
    }

    @Override
    public Page<Object[]> list(PageRequestDTO req) {
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize(), Sort.by("ano").descending());
        return repository.getList(pageable);
    }

}

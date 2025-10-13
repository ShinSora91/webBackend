package com.green.blue.red.service;

import com.green.blue.red.dto.AddressDTO;
import com.green.blue.red.dto.PageRequestDTO;
import com.green.blue.red.dto.PageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface AddressService {
    Long register(AddressDTO dto);
    public Page<Object[]> list(PageRequestDTO req);

}

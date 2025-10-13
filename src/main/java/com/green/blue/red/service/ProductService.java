package com.green.blue.red.service;

import com.green.blue.red.dto.PageRequestDTO;
import com.green.blue.red.dto.PageResponseDTO;
import com.green.blue.red.dto.ProductDTO;

public interface ProductService {
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO dto);
    Long register(ProductDTO dto);
    ProductDTO get(Long pno);
}

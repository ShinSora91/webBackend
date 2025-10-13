package com.green.blue.red.service;

import com.green.blue.red.domain.Product;
import com.green.blue.red.domain.ProductImage;
import com.green.blue.red.dto.PageRequestDTO;
import com.green.blue.red.dto.PageResponseDTO;
import com.green.blue.red.dto.ProductDTO;
import com.green.blue.red.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService{

    private final ProductRepository repository;

    @Override
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO dto) {
        log.info("product Service 전체 조회 : dto:{}",dto);
        Pageable pageable = PageRequest.of(
                dto.getPage()-1, //페이지 시작 번호가 0부터 시작하므로
                dto.getSize(),
                Sort.by("pno").descending()
        );
        Page<Object[]> result = repository.selectList(pageable);
        List<ProductDTO> dtoList = result.get().map(i -> {
            Product product = (Product) i[0];
            ProductImage productImage = (ProductImage) i[1];
            ProductDTO productDTO = ProductDTO.builder()
                    .pno(product.getPno())
                    .pname(product.getPname())
                    .pdesc(product.getPdesc())
                    .price(product.getPrice())
                    .build();
            String imageStr = productImage.getFileName();
            productDTO.setUploadFileNames(List.of(imageStr));
            return productDTO;
        }).toList();
        long totalCount = result.getTotalElements();
        PageResponseDTO<ProductDTO> pagedto = PageResponseDTO.<ProductDTO>withAll()
                .dtoList(dtoList)
                .totalCount(totalCount)
                .pageRequestDTO(dto)
                .build();
//        List<Integer> arr = new ArrayList<>();
//        for(int i=dto.getStart().intValue(); i<dto.getEnd(); i++){
//            arr.add(i);
//        }
//        pagedto.setPageNumList(arr);
        return pagedto;
//        http://localhost:3000/product/list?a=4&b=14

//        return PageResponseDTO.<ProductDTO>withAll()
//                .dtoList(dtoList)
//                .totalCount(totalCount)
//                .pageRequestDTO(dto)
//                .build();
    }
    private Product dtoToEntity(ProductDTO dto){
        Product product = Product.builder()
                .pno(dto.getPno())
                .pname(dto.getPname())
                .pdesc(dto.getPdesc())
                .price(dto.getPrice())
                .build();
        //업로드 처리가 끝난 파일들의 이름 목록
        List<String> uploadFileNames = dto.getUploadFileNames();
        if(uploadFileNames == null) return product;
        uploadFileNames.stream().forEach(i -> {
            log.info("100) toEntity의 메서드 service file name:{}",i);
            product.addImageString(i);
        });
        return product;
    }
    @Override
    public Long register(ProductDTO dto) {
        log.info("service 등록: dto: {} ", dto);
        Product product = dtoToEntity(dto);
        Product result = repository.save(product);
        return result.getPno();
    }

    private ProductDTO entityToDTO(Product product){
        ProductDTO productDTO = ProductDTO.builder()
                .pno(product.getPno())
                .pdesc(product.getPdesc())
                .pname(product.getPname())
                .price(product.getPrice())
                .build();

        List<ProductImage> imageList = product.getImageList();
        if(imageList == null || imageList.size() == 0) return productDTO;
        List<String> fileNameList = imageList.stream().map(productImage ->
                productImage.getFileName()
        ).toList();
        productDTO.setUploadFileNames(fileNameList);
        log.info("fileNameList:{}",fileNameList);
        return productDTO;
    }
    @Override
    public ProductDTO get(Long pno) {
        Optional<Product> result = repository.selectOne(pno);
        Product product = result.orElseThrow();
        ProductDTO productDTO =entityToDTO(product);
        return productDTO;
    }
}

package com.green.blue.red.repository;

import com.green.blue.red.domain.Product;
import com.green.blue.red.domain.ProductImage;
import com.green.blue.red.dto.NaDTO;
import com.green.blue.red.dto.PageResponseDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import java.util.*;

@SpringBootTest
@Slf4j
public class ProductRepositoryTests {

    @Autowired
    ProductRepository repository;

    @Test
    public void testInsert(){
        for (int i=0; i<10; i++){
            Product p = Product.builder()
                    .pname("상품" + i)
                    .price(100 * i)
                    .pdesc("상품 설명" + i)
                    .build();
            p.addImageString("image1.jpg");
            p.addImageString("image2.jpg");
            repository.save(p);
            log.info("========================");
        }
    }

    @Test
    public void testInsert2(){
        for (int i=0; i<100; i++){
            Product p = Product.builder()
                    .pname("상품" + i)
                    .price(100 * i)
                    .pdesc("상품 설명" + i)
                    .build();

            // int num = (int)(Math.random()*5)+3  0~4 생성 후 +3 그러면 3~7 정수 생성
            int num = (int)(Math.random()*5)+3;
            for (int j=0; j<num; j++){

                p.addImageString("image.jpg");

            }
            repository.save(p);
        }
    }

    @Test
    public void testInsert3(){
        int i = 0;
        int cnt = 0;
        do {
            Product p = Product.builder()
                    .pname("상품" + i)
                    .price(100 * i)
                    .pdesc("상품 설명" + i)
                    .build();
            do {
                cnt = 0;
                int z = (int)(Math.random()*10);
                if(z >= 3 && z <= 7){
                    for (int j=0; j<z; j++){
                        p.addImageString(UUID.randomUUID() + "_image.jpg");
                        cnt++;
                    }
                    break;
                }
                log.info("i={}, cnt={}, j={}", i, z, cnt);
            } while (cnt<100);
            repository.save(p);
            i++;
        } while (i<100);
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteUmage(){
        repository.updateToDelete(11l,true);
    }

    @Test
    public void testRead2(){
        Optional<Product> result = repository.selectOne(11l);
        Product product = result.orElseThrow();
        log.info("{}",product);
        log.info("{}",product.getImageList());
    }

//    NaDTO toNaDTO(Product product){
//        return NaDTO.builder()
//                .pname(product.getPname())
//                .price(product.getPrice())
//                .pdesc(product.getPdesc())
//                .pno(product.getPno())
//                .build();
//    }

    @Test
    public void testNaDTO(){
        Long [] dtoPnoList = {11l,12l,13l,14l,15l};
        List<NaDTO> naDTOList = new ArrayList<>();
        // db에서 selectOne로 조회하여

        for(int i=0; i<dtoPnoList.length; i++) {
            Optional<Product> pr = repository.selectOne(dtoPnoList[i]);
            if (pr.isPresent()) {
                Product product = pr.get();
                List<String> imageList = new ArrayList<>();
                for (ProductImage j : product.getImageList()) imageList.add(j.getFileName());
                NaDTO naDTO = NaDTO.builder()
                        .pno(product.getPno())
                        .pname(product.getPname())
                        .pdesc(product.getPdesc())
                        .price(product.getPrice())
                        .build();
                naDTOList.add(naDTO);
            }
        }

//        Arrays.stream(dtoPnoList).forEach(i->{
//            Product en = repository.selectOne(i).get();
//            NaDTO.builder()
//                    .pname(en.getPname())
//                    .pdesc(en.getPdesc())
//                    .price(en.getPrice())
//                    .pno(en.getPno())
//                    .fileNames(en.getImageList().stream().map(v->v.getFileName()).toList())
//                    .build();
//        });
        log.info("naDtoList=> {}", naDTOList);
    }

    @Test
    public void testDelete(){
        repository.deleteById(11l);
    }

    @Test
    public void testUpdate(){
        Long pno = 12l;
        Product product = repository.selectOne(pno).get();
        product.changeName("12번 상품");
        product.changeDesc("12번 상품 설명");
        product.changePrice(3400);
        //첨부 파일 수정
        product.clearList();
        product.addImageString(UUID.randomUUID().toString()+"_"+"NewIMAGE1.jpg");
        product.addImageString(UUID.randomUUID().toString()+"_"+"NewIMAGE2.jpg");
        product.addImageString(UUID.randomUUID().toString()+"_"+"NewIMAGE3.jpg");
        repository.save(product);
    }

    @Test
    public void testList(){
        Pageable pageable = PageRequest.of(0,10, Sort.by("pno").descending());
        Page<Object[]> result = repository.selectList(pageable);
        result.getContent().forEach(i -> log.info("data=> {}",i));
    }

    @Test
    public void testList2(){
        for(int i=0; i<100; i++){
            Pageable pageable = PageRequest.of(0+i,10, Sort.by("pno").descending());
            Page<Object[]> result = repository.selectList(pageable);
            result.getContent().forEach(j -> log.info("data=> {}",j));
        }
    }

  @Test
  public void testList3(){
      Pageable pageable = PageRequest.of(0,10, Sort.by("pno").descending());
      Page<Object[]> result = repository.selectList(pageable);

      int totalPages = result.getTotalPages();
      log.info("totalPages={}", totalPages);

      for(int i=0; i<totalPages; i++){
        Pageable currentPageable = PageRequest.of(i, 10, Sort.by("pno").descending());
        Page<Object[]> currentPage = repository.selectList(currentPageable);

        log.info("현재 페이지: {}", i + 1);
        currentPage.getContent().forEach(row -> log.info("data => {}", Arrays.toString(row)));
      }
  }
}

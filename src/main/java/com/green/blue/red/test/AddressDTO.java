package com.green.blue.red.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long ano;
    private String city;
    private String gu;
    private String dong;
    private String name;
    private int age;

    @Builder.Default
    private List<MultipartFile> fileList= new ArrayList<>();

    @Builder.Default
    private List<String> uploadFile =new ArrayList<>();
}

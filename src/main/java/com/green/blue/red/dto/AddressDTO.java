package com.green.blue.red.dto;

import com.green.blue.red.domain.AddressImage;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private Long ano;
    private int age;
    private String dong;
    private String gu;
    private String city;
    private String name;

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();

    @Builder.Default
    private List<String> uploadFileName = new ArrayList<>();
}

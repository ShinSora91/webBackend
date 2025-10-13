package com.green.blue.red.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_address")
@Getter
@ToString(exclude = "addImageList")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ano;
    private int age;
    private String dong;
    private String gu;
    private String city;
    private String name;

    @ElementCollection
    @Builder.Default
    private List<AddressImage> addImageList = new ArrayList<>();

    public void changeAge(int age) { this.age=age; }
    public void changeDong(String dong) { this.dong=dong; }
    public void changeGu(String gu) { this.gu=gu; }
    public void changeCity(String city) { this.city=city; }
    public void changeName(String name) { this.name=name; }

    public void addImage(AddressImage image){
        image.setOrd(this.addImageList.size());
        addImageList.add(image);
    }

    public void addImageString(String fileName){
        AddressImage addressImage = AddressImage.builder()
                .fileName(fileName)
                .build();
        addImage(addressImage);
    }
    public void clearList() { this.addImageList.clear(); }
}

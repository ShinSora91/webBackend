package com.green.blue.red.dto;

import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NaDTO {

    private Long pno;
    private String pname;
    private int price;
    private String pdesc;

    private List<String> fileNames = new ArrayList<>();
}

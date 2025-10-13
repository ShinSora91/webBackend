package com.green.blue.red.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {// todo/list?page=3&size=25
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

}

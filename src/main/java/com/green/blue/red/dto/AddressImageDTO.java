package com.green.blue.red.dto;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressImageDTO {
    private String fileName;
    private int ord;
    private String uuid;
    public void setOrd(int ord) { this.ord=ord; }
}

package com.green.blue.red.dto;

import com.green.blue.red.domain.Cart;
import com.green.blue.red.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Data
public class CartItemDTO {

    private String email;
    private Long pno;
    private int qty;
    private Long cino;

}

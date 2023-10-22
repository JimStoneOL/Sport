package com.marat.controlworkbymarat.dto;

import lombok.Data;

@Data
public class OrderedProductDTO {
    private Long id;
    private long order;
    private String product;
    private int count;
}

package com.marat.controlworkbymarat.dto;

import com.marat.controlworkbymarat.entity.enums.EStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private String orderedProducts;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String issue;
    private String user;
    private int code;
    private String eStatus;
}

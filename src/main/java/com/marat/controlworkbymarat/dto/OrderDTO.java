package com.marat.controlworkbymarat.dto;

import com.marat.controlworkbymarat.entity.Issue;
import com.marat.controlworkbymarat.entity.enums.EStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private List<Long> orderedProducts;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private Long issue;
    private Long userId;
    private int code;
    private EStatus eStatus;

}

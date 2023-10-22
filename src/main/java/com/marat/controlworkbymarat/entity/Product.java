package com.marat.controlworkbymarat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Product {

    @Id
    private String article;

    private String name;
    private int price;
    private int discountSize;
    private String company;
    private String provider;
    private String category;
    private int discount;
    private int count;
    @Column(columnDefinition = "text")
    private String description;
    private String img;
}

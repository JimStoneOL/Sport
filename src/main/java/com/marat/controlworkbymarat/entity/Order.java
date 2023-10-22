package com.marat.controlworkbymarat.entity;

import com.marat.controlworkbymarat.entity.enums.EStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "order")
    private List<OrderedProduct> orderedProductList;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private Issue issue;
    @ManyToOne(cascade = CascadeType.REFRESH)
    private User user;
    private int code;
    private EStatus eStatus;
}

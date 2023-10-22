package com.marat.controlworkbymarat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text",unique = true)
    private String name;
    @OneToMany(mappedBy = "issue")
    private List<Order> orderList;
}

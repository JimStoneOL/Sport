package com.marat.controlworkbymarat.repository;

import com.marat.controlworkbymarat.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}

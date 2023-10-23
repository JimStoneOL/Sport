package com.marat.controlworkbymarat.repository;

import com.marat.controlworkbymarat.entity.Order;
import com.marat.controlworkbymarat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUser(User user);
}

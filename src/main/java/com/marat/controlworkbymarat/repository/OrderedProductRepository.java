package com.marat.controlworkbymarat.repository;

import com.marat.controlworkbymarat.entity.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct,Long> {
}

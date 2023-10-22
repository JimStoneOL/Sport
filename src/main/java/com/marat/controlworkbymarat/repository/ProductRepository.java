package com.marat.controlworkbymarat.repository;

import com.marat.controlworkbymarat.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}

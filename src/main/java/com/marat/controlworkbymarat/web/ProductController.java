package com.marat.controlworkbymarat.web;

import com.marat.controlworkbymarat.dto.Message;
import com.marat.controlworkbymarat.entity.Product;
import com.marat.controlworkbymarat.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public Message createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @GetMapping("/get/{id}")
    public Product getProductById(@PathVariable String id){
        return productService.getProductById(id);
    }

    @GetMapping("/get/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

}

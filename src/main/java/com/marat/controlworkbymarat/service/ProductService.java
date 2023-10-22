package com.marat.controlworkbymarat.service;

import com.marat.controlworkbymarat.dto.Message;
import com.marat.controlworkbymarat.entity.Product;
import com.marat.controlworkbymarat.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Message createProduct(Product product){
        product.setArticle(generateCode());
        productRepository.save(product);
        return new Message("Продукт создан");
    }

    public int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public String generateCode() {
        int leftLimit = 48;
        int rightLimit = 122;
        final int min = 5;
        final int max = 5;
        int targetStringLength = rnd(min,max);
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString().toUpperCase();

        return generatedString;
    }

    public Product getProductById(String article){
        return productRepository.findById(article).orElse(null);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}

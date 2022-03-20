package com.example.demo.service;

import com.example.demo.dto.ProductDetails;
import com.example.demo.entity.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    @Value("${spring.datasource.password}")
    private String databasePassword;
    @Value("${spring.datasource.username}")
    private String databaseUserName;
    @Value("${spring.datasource.url}")
    private String databaseUrl;

    public String insertProduct(Product product) {
        try {
            productRepository.save(product);
        } catch (IllegalArgumentException e) {
            return "Please provide a valid product";
        }
        return "Successfully inserted a product";
    }


    public Iterable<Product> loadAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProductById(int productId) {
        productRepository.deleteById(productId);
    }

    public String updateProduct(Product product) {
        try {
            productRepository.save(product);
        } catch (IllegalArgumentException e) {
            return "Please provide a valid product";
        }
        return "Successfully updated the product product";
    }
}

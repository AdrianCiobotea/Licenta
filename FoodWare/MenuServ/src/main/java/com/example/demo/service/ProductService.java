package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    public Optional<Product> loadProductById(int productId) {
        return productRepository.findById(productId);
    }

    public String deleteProductById(int productId) {
        productRepository.deleteById(productId);
        return "Successfully deleted product with id " + productId;
    }

    public String updateProduct(Product product,int id) {
        try {
            Optional<Product> productDB = loadProductById(id);
            if (productDB.isPresent()) {
                productDB.get().setCategory(product.getCategory());
                productDB.get().setDescription(product.getDescription());
                productDB.get().setName(product.getName());
                productDB.get().setPrice(product.getPrice());
                productDB.get().setIsExtra(product.getIsExtra());
                productDB.get().setImage(product.getImage());
            }
            productRepository.save(product);
        } catch (IllegalArgumentException e) {
            return "Please provide a valid product";
        }
        return "Successfully updated the product";
    }
}

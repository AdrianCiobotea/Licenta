package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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

  public String updateProduct(Product product, int id) {
    try {
      Product productDB = loadProductById(id).get();
      if (product.getCategoryId() == 0) {
        productDB.setCategoryId(productDB.getCategoryId());
      } else {
        productDB.setCategoryId(product.getCategoryId());
      }
      if (product.getDescription() == null || product.getDescription() == "") {
        productDB.setDescription(productDB.getDescription());
      } else {
        productDB.setDescription(product.getDescription());
      }
      if (product.getName() == null || product.getName() == "") {
        productDB.setName(productDB.getName());
      } else {
        productDB.setName(product.getName());
      }
      if (product.getPrice() == 0) {
        productDB.setPrice(productDB.getPrice());
      } else {
        productDB.setPrice(product.getPrice());
      }
      if (product.getIsExtra() == null) {
        productDB.setIsExtra(productDB.getIsExtra());
      } else {
        productDB.setIsExtra(product.getIsExtra());
      }
      if (product.getImageId() == null) {
        productDB.setImageId(productDB.getImageId());
      } else {
        productDB.setImageId(product.getImageId());
      }
      productRepository.save(productDB);
    } catch (NoSuchElementException e) {
      if (product.getName() != null && product.getName() == "" && product.getPrice() != 0.0 &&
          product.getIsExtra() != null || product.getDescription() != null || product.getDescription() != "") {
        productRepository.save(product);
      } else {
        return "Please provide a valid product";
      }
    } catch (IllegalArgumentException e) {
      return "Please provide a valid product";
    }
    return "Successfully updated the product";
  }
}

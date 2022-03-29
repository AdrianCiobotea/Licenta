package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
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
      Optional<Product> productDB = loadProductById(id);
      if (productDB.isPresent()) {
        if (product.getCategoryId() == 0) {
          productDB.get().setCategoryId(productDB.get().getCategoryId());
        } else {
          productDB.get().setCategoryId(product.getCategoryId());
        }
        if (product.getDescription() == null || product.getDescription() == "") {
          productDB.get().setDescription(productDB.get().getDescription());
        } else {
          productDB.get().setDescription(product.getDescription());
        }
        if (product.getName() == null || product.getName() == "") {
          productDB.get().setName(productDB.get().getName());
        } else {
          productDB.get().setName(product.getName());
        }
        if (product.getPrice() == 0) {
          productDB.get().setPrice(productDB.get().getPrice());
        } else {
          productDB.get().setPrice(product.getPrice());
        }
        if (product.getIsExtra() == null) {
          productDB.get().setIsExtra(productDB.get().getIsExtra());
        } else {
          productDB.get().setIsExtra(product.getIsExtra());
        }
        if (product.getImageURL() == null) {
          productDB.get().setImageURL(productDB.get().getImageURL());
        } else {
          productDB.get().setImageURL(product.getImageURL());
        }

      }
      productRepository.save(product);
    } catch (IllegalArgumentException e) {
      return "Please provide a valid product";
    }
    return "Successfully updated the product";
  }
}

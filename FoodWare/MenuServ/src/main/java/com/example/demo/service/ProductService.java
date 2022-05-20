package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.BaseProductRepository;
import com.example.demo.repository.ImageRepository;
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
  BaseProductRepository baseProductRepository;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  CategoryService categoryService;

  @Autowired
  ImageRepository imageRepository;

  @Value("${spring.datasource.password}")
  private String databasePassword;
  @Value("${spring.datasource.username}")
  private String databaseUserName;
  @Value("${spring.datasource.url}")
  private String databaseUrl;

  public String insertProduct(Product product) {
    try {
      baseProductRepository.save(product);
    } catch (IllegalArgumentException e) {
      return "Please provide a valid product";
    }
    return "Successfully inserted a product";
  }


  public List<Product> loadAllProducts() {
    List<Product> products = new ArrayList<>();
    baseProductRepository.findAll().forEach(products::add);
    return products;
  }

  public List<Product> loadAllProductsByCategoryId(int categoryId) {
    List<Product> products = new ArrayList<>();
    productRepository.findProductByCategoryIdOrderByName(categoryId).forEach(products::add);
    return products;
  }

  public List<Product> loadAllProductsByGroupId(int groupId) {
    List<Category> categoriesByGroup = categoryService.loadAllCategoriesByGroupId(groupId);
    List<Product> products = new ArrayList<>();
    for (Category category : categoriesByGroup) {
      productRepository.findProductByCategoryIdOrderByName(category.getId()).forEach(products::add);
    }
    return products;
  }

  public Optional<Product> loadProductById(int productId) {
    return baseProductRepository.findById(productId);
  }

  public String deleteProductById(int productId) {
    try {
      int imageId = baseProductRepository.findById(productId).get().getImageId();
      baseProductRepository.deleteById(productId);
      imageRepository.deleteById(imageId);
      return "Successfully deleted product with id " + productId;
    } catch (NoSuchElementException e) {
      log.info("The product with the id: " + productId + " doesn't exist");
      return "The product with the id: " + productId + " doesn't exist";
    }

  }

  public String updateProduct(Product product, int id) {
    try {
      Product productDB = loadProductById(id).get();
      if (product.getCategoryId() == null) {
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
      if (product.getImageId() == null) {
        productDB.setImageId(productDB.getImageId());
      } else {
        productDB.setImageId(product.getImageId());
      }
      baseProductRepository.save(productDB);
    } catch (NoSuchElementException e) {
      if (product.getName() != null && product.getName() == "" && product.getPrice() != 0.0 &&
          product.getDescription() != null && product.getDescription() != "") {
        baseProductRepository.save(product);
      } else {
        return "Please provide a valid product";
      }
    } catch (IllegalArgumentException e) {
      return "Please provide a valid product";
    }
    return "Successfully updated the product";
  }
}

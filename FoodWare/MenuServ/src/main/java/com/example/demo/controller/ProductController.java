package com.example.demo.controller;

import com.example.demo.dto.ProductDetails;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

  @Autowired
  ProductService productService;

  @PostMapping(path = "insert")
  public String addProduct(@RequestBody Product product) {
    return productService.insertProduct(product);
  }

  @GetMapping()
  public List<ProductDetails> getAllProducts() {
    return productService.loadAllProducts();
  }
}

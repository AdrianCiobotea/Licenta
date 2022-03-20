package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
  public Iterable<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();
    productService.loadAllProducts().forEach(products::add);
    return products;
  }

  @PostMapping(path = "delete/{id}")
  public void deleteProduct(@PathVariable Integer id) {
    productService.deleteProductById(id);
  }
}

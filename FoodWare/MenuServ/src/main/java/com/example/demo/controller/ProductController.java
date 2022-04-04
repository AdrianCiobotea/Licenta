package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ImageService;
import com.example.demo.service.ProductService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

  @Autowired
  ProductService productService;

  @Autowired
  ImageService imageService;

  @PostMapping(path = "insert", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public String addProduct(@RequestPart Product product, @RequestPart MultipartFile image) {
    int imageId = imageService.insertImage(image);
    product.setImageId(imageId);
    return productService.insertProduct(product);
  }

  @GetMapping()
  public Iterable<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();
    productService.loadAllProducts().forEach(products::add);
    return products;
  }

  @GetMapping(path = "/{id}")
  public java.util.Optional<Product> getProductById(@PathVariable Integer id) {
    return productService.loadProductById(id);
  }

  @PostMapping(path = "update/{id}")
  public String updateProduct(@PathVariable Integer id, @RequestBody Product product) {
    return productService.updateProduct(product, id);
  }

  @PostMapping(path = "delete/{id}")
  public String deleteProduct(@PathVariable Integer id) {
    return productService.deleteProductById(id);
  }

  @GetMapping(path = "image/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public byte[] getImageById(@PathVariable Integer id) throws SQLException {
    return imageService.loadImageById(id);
  }

  @PostMapping(path = "image/delete/{id}")
  public String deleteImage(@PathVariable Integer id) {
    return imageService.deleteImageByProductId(id);
  }
}

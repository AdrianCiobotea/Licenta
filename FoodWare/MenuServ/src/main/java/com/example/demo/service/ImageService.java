package com.example.demo.service;

import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import com.example.demo.repository.ImageRepository;
import java.io.IOException;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ImageService {
  @Autowired
  ImageRepository imageRepository;

  @Autowired
  ProductService productService;

  @Value("${spring.datasource.password}")
  private String databasePassword;
  @Value("${spring.datasource.username}")
  private String databaseUserName;
  @Value("${spring.datasource.url}")
  private String databaseUrl;

  public int insertImage(MultipartFile image) {
    Image imageDB = null;
    try {
      imageDB = imageRepository.save(new Image(image.getBytes()));
    } catch (IllegalArgumentException e) {
      log.info(e.getMessage());
      return -1;
    } catch (IOException exception) {
      log.info(exception.getMessage());
    }
    return imageDB.getId();
  }

  public byte[] loadImageById(int productId) {
    int imageId = 0;
    try {
      Product product = productService.loadProductById(productId).get();
      imageId = product.getImageId();
    } catch (NoSuchElementException e) {
      return null;
    }
    byte[] image = null;
    try {
      image = imageRepository.findById(imageId).get().getImage();
      return image;
    } catch (NoSuchElementException e) {
      log.info("There was no image for provided imageID: " + imageId);
      return null;
    }

  }

  public String deleteImageByProductId(int productId) {
    int imageId = 0;
    try {
      Product product = productService.loadProductById(productId).get();
      imageId = product.getImageId();
      product.setImageId(null);
      productService.updateProduct(product, productId);
      imageRepository.deleteById(imageId);
    } catch (NoSuchElementException e) {
      log.info("There is no product for the given ID : " + productId + " " + e.getMessage());
      return "There is no product for the given ID : " + productId;
    }
    return "Successfully deleted image with id " + imageId;
  }

  public String updateImage(MultipartFile image, int id) {
    Image imageDB = imageRepository.findById(id).get();
    try {
      imageDB.setImage(image.getBytes());
    } catch (IOException e) {
      log.info("Could not get bytes from image file " + e.getMessage());
      return "The provided image is not valid";
    }
    return "Successfully updated the image with id: " + id;
  }
}

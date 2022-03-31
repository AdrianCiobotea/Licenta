package com.example.demo.service;

import com.example.demo.entity.Image;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class ImageService {
  @Autowired
  ImageRepository imageRepository;

  @Value("${spring.datasource.password}")
  private String databasePassword;
  @Value("${spring.datasource.username}")
  private String databaseUserName;
  @Value("${spring.datasource.url}")
  private String databaseUrl;

  public int insertImage(Image image) {
    Image imageDB = null;
    try {
      imageDB = imageRepository.save(image);
    } catch (IllegalArgumentException e) {
      return -1;
    }
    return imageDB.getId();
  }
}

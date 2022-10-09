package com.example.demo.controller;

import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.util.OkHttp;

@RestController
@RequestMapping(path = "menu")
public class MenuController {
  @GetMapping(path = "category")
  public String redirectMenu() {
    try {
      return OkHttp.getRequest("http://localhost:8084/category");
    } catch (IOException e) {
      System.out.println("could not parse the response");
    }
    return null;
  }
}

package com.example.demo.controller;

import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.OkHttp;

@RestController
@RequestMapping(path = "access")
public class BffController {

  @GetMapping(path = "pay")
  public String redirectPayment() {
    try {
      return OkHttp.getRequest("http://localhost:8083/payment");
    } catch (IOException e) {
      System.out.println("could not parse the response");
    }
    return null;
  }
  @GetMapping(path = "order")
  public String redirectOrder() {
    try {
      return OkHttp.getRequest("http://localhost:8082/order");
    } catch (IOException e) {
      System.out.println("could not parse the response");
    }
    return null;
  }

}

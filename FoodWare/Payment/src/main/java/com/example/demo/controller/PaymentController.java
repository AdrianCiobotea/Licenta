package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "payment")
public class PaymentController {
  @GetMapping
  public String pay() {

    return "payment application";
  }
}

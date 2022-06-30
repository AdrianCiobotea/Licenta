package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Payment {
  private int id;
  private double amount;
  private String method;
}

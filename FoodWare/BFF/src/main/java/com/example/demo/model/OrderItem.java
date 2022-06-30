package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
  private int id;
  private int quantity;
  private int sub_order_id;
  private int product_id;
  private int status_id;
  // add list of extraIds
}

package com.example.demo.model;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
  private String id;
  private int quantity;
  private int subOrderId;
  private int productId;
  private int statusId;
  // add list of extraIds
  private List<Integer> extraIds;
}

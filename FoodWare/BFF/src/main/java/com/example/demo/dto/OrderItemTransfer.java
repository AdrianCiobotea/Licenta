package com.example.demo.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemTransfer {
  private int id;
  private int quantity;
  private int subOrderId;
  private int productId;
  private int statusId;
  private List<Integer> extraIds;
}

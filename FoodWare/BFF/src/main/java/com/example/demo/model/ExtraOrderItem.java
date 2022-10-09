package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExtraOrderItem {
  private int id;
  private String orderItemId;
  private int extraId;
}

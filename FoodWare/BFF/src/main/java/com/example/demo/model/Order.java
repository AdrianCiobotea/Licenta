package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order {
  private int id;
  private int initiator_id;
  private int table_id;

}


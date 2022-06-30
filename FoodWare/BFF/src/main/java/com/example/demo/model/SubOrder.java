package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SubOrder {
  private int id;
  private int payment_id;
  private int status_id;
  private int user_id;
  // de adaugat si in order microservice
  private int order_id;
}

package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "restaurant_sub_order")
public class SubOrder {
  @Id
  @GeneratedValue(
      strategy = GenerationType.IDENTITY
  )
  private int id;
  @Column(nullable = false, name = "payment_id")
  private int payment_id;
  @Column(nullable = false, name = "status_id")
  private int status_id;
  @Column(nullable = false, name = "user_id")
  private int user_id;
}

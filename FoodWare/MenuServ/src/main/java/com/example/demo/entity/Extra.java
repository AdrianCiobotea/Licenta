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
@Table(name = "extra")
public class Extra {
  @Id
  @GeneratedValue(
      strategy = GenerationType.IDENTITY
  )
  private int id;
  @Column(name = "name")
  private String name;
  @Column(name = "price")
  private double price;
  @Column(name = "category_id")
  private int categoryId;
}

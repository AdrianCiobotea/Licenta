package com.example.demo.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(
      strategy = GenerationType.IDENTITY
  )
  private int id;
  @Column(nullable = false, name = "name")
  private String name;
  @Column(nullable = false, name = "price")
  private double price;
  @Column(nullable = false, name = "description")
  private String description;
  //@ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "category_id", nullable = false)
  private Integer categoryId;
  @Column(name = "image_id")
  private Integer imageId;
  @Column(name = "is_extra", nullable = false)
  private Boolean isExtra;
}

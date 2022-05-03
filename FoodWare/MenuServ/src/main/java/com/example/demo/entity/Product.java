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
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "category_id", nullable = false,referencedColumnName = "id")
  private Category category;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "image_id",referencedColumnName = "id")
  private Image image;
}

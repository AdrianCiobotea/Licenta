package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
//@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int id;
    @Column(nullable = false, name = "name")
    private String name;
    @Column(nullable = false, name = "price")
    private double price;
    @Column(nullable = false, name = "description")
    private String description;
    //@ManyToOne(cascade= CascadeType.ALL , mappedBy = "product")
    //@JoinColumn(name="category_id", nullable=false)
    private int category_id;
    @Column(name = "image")
    private byte[] image;
    @Column(name="extra")
    private Boolean extra;
}

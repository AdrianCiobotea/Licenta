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
//@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private int id;
    @Column(nullable = false,name="name")
    private String name;
    //@ManyToOne
    //@JoinColumn(name="group_id", nullable=false)
    private int group_id;
    //@OneToMany(mappedBy = "category")
    //private List<Product> products;
}

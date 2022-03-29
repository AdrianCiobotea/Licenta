package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
  @Id
  @GeneratedValue(
      strategy = GenerationType.IDENTITY
  )
  private int id;
  @Column(nullable = false, name = "name")
  private String name;
  //@ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "group_id")
  private int groupId;
//    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
//    private List<Product> products;
}

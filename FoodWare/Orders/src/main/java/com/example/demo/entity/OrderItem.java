package com.example.demo.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "restaurant_order_item")
public class OrderItem {
  @Id
  @GeneratedValue(
      strategy = GenerationType.IDENTITY
  )
  private int id;
  @Column(nullable = false, name = "quantity")
  private int quantity;
  @Column(name = "sub_order_id")
  private int subOrderId;
  @Column(name = "product_id")
  private int productId;
  @Column(name = "status_id")
  private int statusId;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "id")
  private List<ExtraOrderItem> extraOrderItems;
  //create list of extra order items as list of strings which are saved as one to many in the database

}

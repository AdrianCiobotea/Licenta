package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {
  @Id
  @GeneratedValue(
      strategy = GenerationType.IDENTITY
  )
  private int id;
  @Column(nullable = false, name = "initiator_id")
  private int initiator_id;
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "payment_id")
  private int payment_id;
}


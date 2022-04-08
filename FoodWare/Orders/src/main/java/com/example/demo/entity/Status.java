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
@Table(name = "status")
public class Status {
  @Id
  @GeneratedValue(
      strategy = GenerationType.IDENTITY
  )
  private int id;
  @Column(nullable = false, name = "status")
  private String status;
}

package com.foodware.auth.entity.user;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class User {

  @Id
  @GeneratedValue(
      strategy = GenerationType.AUTO
  )
  private Integer id;
  private String phoneNumber;
  private String password;
  @Enumerated(EnumType.STRING)
  private Role userRole;

  public User(String password, String phoneNumber, Role userRole) {
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.userRole = userRole;
  }
}



package com.foodware.auth.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRequest {
  private String phoneNumber;
  private String password;
}

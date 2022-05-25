package com.foodware.auth.entity.user;

public enum Role {
  ADMIN,
  CLIENT;

  public String authority() {
    return "ROLE_" + this.name();
  }
}

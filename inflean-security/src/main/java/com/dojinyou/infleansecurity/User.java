package com.dojinyou.infleansecurity;

public class User {

  final String id;
  final String password;
  Role role;

  String encodedPassword;

  public User(
      String id,
      String password
  ) {
    this.id = id;
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public String getPassword() {
    return password;
  }

  public String getEncodedPassword() {
    return encodedPassword;
  }

  public void setEncodedPassword(String encodedPassword) {
    this.encodedPassword = encodedPassword;
  }

  public Role getRole() {
    return role;
  }

  public String getRoleName() {
    return role.name();
  }

  public void setRole(String name) {
    this.role = Role.valueOf(name);
  }

  public enum Role {
    ROLE_USER
  }
}

package com.dojinyou.infleansecurity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private final List<User> db = new ArrayList<>();

  public Optional<User> findById(String id) {
    return db
        .stream()
        .filter(user -> user.id.equals(id))
        .findFirst();
  }

  public void addUser(User user) {
    if (user != null) {
      db.add(user);
    }
  }

  public List<User> findAll() {
    return db;
  }
}

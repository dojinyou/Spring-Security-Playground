package com.dojinyou.infleansecurity;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("api/v1/users")
public class UserController {

  private final UserRepository repository;
  private final BCryptPasswordEncoder passwordEncoder;

  public UserController(
      UserRepository repository,
      BCryptPasswordEncoder passwordEncoder
  ) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping
  public String addUser(@ModelAttribute User user) {
    String encodedPassword = passwordEncoder.encode(user.getPassword());
    user.setEncodedPassword(encodedPassword);
    user.setRole(User.Role.ROLE_USER.name());

    repository.addUser(user);

    return "redirect:/login";
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody User findById(@PathVariable String id) {
    return repository
        .findById(id)
        .orElse(new User("notFound", "notFound"));

  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public @ResponseBody List<User> findAll() {
    return repository.findAll();

  }

}

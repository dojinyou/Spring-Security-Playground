package com.dojinyou.infleansecurity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewController {

  @GetMapping("/login")
  public String login() {
    System.out.println("ViewController - login");
    return "login";
  }

  @GetMapping("signup")
  public String signup() {
    System.out.println("ViewController - signUp");
    return "signup";
  }

}

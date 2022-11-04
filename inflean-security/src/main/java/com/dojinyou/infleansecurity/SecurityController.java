package com.dojinyou.infleansecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {

  @GetMapping("/auth")
  public String auth() {
    System.out.println("Security Auth");
    return "Auth";
  }

  @GetMapping("/no-auth")
  public String noAuth() {
    return "No Auth";
  }

}

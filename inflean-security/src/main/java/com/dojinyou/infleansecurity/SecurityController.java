package com.dojinyou.infleansecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class SecurityController {

  @GetMapping("/home")
  public String index() {
    return "home";
  }

}

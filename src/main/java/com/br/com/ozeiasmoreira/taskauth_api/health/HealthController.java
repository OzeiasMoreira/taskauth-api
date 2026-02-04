package com.br.com.ozeiasmoreira.taskauth_api.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

  @GetMapping("/")
  public String home() {
    return "TaskAuth API is running";
  }

  @GetMapping("/health")
  public String health() {
    return "OK";
  }
}

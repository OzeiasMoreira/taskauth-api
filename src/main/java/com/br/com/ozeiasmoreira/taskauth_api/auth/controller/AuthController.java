package com.br.com.ozeiasmoreira.taskauth_api.auth.controller;

import com.br.com.ozeiasmoreira.taskauth_api.auth.dto.LoginRequest;
import com.br.com.ozeiasmoreira.taskauth_api.auth.dto.RegisterRequest;
import com.br.com.ozeiasmoreira.taskauth_api.auth.dto.TokenResponse;
import com.br.com.ozeiasmoreira.taskauth_api.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/register")
  public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest req) {
    authService.register(req);
    return ResponseEntity.status(201).build();
  }

  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest req) {
    return ResponseEntity.ok(authService.login(req));
  }
}

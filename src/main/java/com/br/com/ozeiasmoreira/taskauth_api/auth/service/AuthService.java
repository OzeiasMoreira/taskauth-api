package com.br.com.ozeiasmoreira.taskauth_api.auth.service;

import com.br.com.ozeiasmoreira.taskauth_api.auth.dto.LoginRequest;
import com.br.com.ozeiasmoreira.taskauth_api.auth.dto.RegisterRequest;
import com.br.com.ozeiasmoreira.taskauth_api.auth.dto.TokenResponse;
import com.br.com.ozeiasmoreira.taskauth_api.user.entity.Role;
import com.br.com.ozeiasmoreira.taskauth_api.user.entity.User;
import com.br.com.ozeiasmoreira.taskauth_api.user.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  public void register(RegisterRequest req) {
    if (userRepository.existsByEmail(req.email())) {
      throw new IllegalArgumentException("Email já cadastrado.");
    }

    User user = User.builder()
      .email(req.email().toLowerCase())
      .passwordHash(passwordEncoder.encode(req.password()))
      .roles(Set.of(Role.ROLE_USER))
      .build();

    userRepository.save(user);
  }

  public TokenResponse login(LoginRequest req) {
    var user = userRepository.findByEmail(req.email().toLowerCase())
      .orElseThrow(() -> new BadCredentialsException("Credenciais inválidas"));

    if (!passwordEncoder.matches(req.password(), user.getPasswordHash())) {
      throw new BadCredentialsException("Credenciais inválidas");
    }

    String token = jwtService.generateToken(user.getEmail());
    return new TokenResponse(token);
  }
}

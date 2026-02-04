package com.br.com.ozeiasmoreira.taskauth_api.auth.service;

import com.br.com.ozeiasmoreira.taskauth_api.user.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public AppUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = userRepository.findByEmail(username)
      .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

    var authorities = user.getRoles().stream()
      .map(r -> new SimpleGrantedAuthority(r.name()))
      .toList();

    return new org.springframework.security.core.userdetails.User(
      user.getEmail(),
      user.getPasswordHash(),
      authorities
    );
  }
}

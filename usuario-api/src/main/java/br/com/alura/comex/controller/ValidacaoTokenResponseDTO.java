package br.com.alura.comex.controller;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public record ValidacaoTokenResponseDTO(
  String name,
  boolean authenticated,
  Collection<GrantedAuthority> authorities
) {

}

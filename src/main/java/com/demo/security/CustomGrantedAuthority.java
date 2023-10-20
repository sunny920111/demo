package com.demo.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {
  private final String role;
  @Getter private final String description;

  @Override
  public String getAuthority() {
    return role;
  }
}

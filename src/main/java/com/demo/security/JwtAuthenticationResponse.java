package com.demo.security;

import lombok.*;

@Getter
@Setter
public class JwtAuthenticationResponse {
  private String accessToken;
  private String tokenType = "Bearer ";
  private Boolean isExpiredPassword = false;

  public JwtAuthenticationResponse(String accessToken) {
    this.accessToken = accessToken;
  }

  public JwtAuthenticationResponse(String accessToken, Boolean isExpiredPassword) {
    this.accessToken = accessToken;
    this.isExpiredPassword = isExpiredPassword;
  }
}

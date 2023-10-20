package com.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenProvider {

  @Value("${security.jwtSecret}")
  private String jwtSecret;

  @Value("${security.jwtExpirationInMs}")
  private int jwtExpirationInMs;

  public String generateJwtToken(Authentication authentication) {
    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
    return this.generateJwtToken(userPrincipal);
  }

  public String generateJwtToken(UserPrincipal userPrincipal) {

    Date now = new Date();
    Date expireDate = new Date(now.getTime() + jwtExpirationInMs);

    return Jwts.builder()
        .setSubject(userPrincipal.getUserId())
        .setIssuedAt(new Date())
        .setExpiration(expireDate)
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }
}

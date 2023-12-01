package com.demo.security;

import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class JwtTokenProvider {

  @Autowired private CustomUserDetailService customUserDetailService;

  @Value("${security.jwtSecret}")
  private String jwtSecret;

  @Value("${security.jwtExpirationInMs}")
  private int jwtExpirationInMs;

  public String getJwtTokenFromHttepRequest(HttpServletRequest httpServletRequest) {
    String bearerToken = httpServletRequest.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7, bearerToken.length());
    }
    return null;
  }

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

  public Optional<Claims> validateToken(String jwtToken) {
    try {

      return Optional.ofNullable(
          Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody());

    } catch (Exception e) {
      // 에러메시지 분기해야함
      System.out.println(e.getMessage());
    }
    return Optional.empty();
  }

  public Authentication getAuthentication(Claims claims) {
    String userId = claims.getSubject();
    UserDetails userDetails = customUserDetailService.loadUserByUserId(userId);

    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  }
}

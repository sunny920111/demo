package com.demo.security;

import com.demo.common.exception.AppException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired private JwtTokenProvider jwtTokenProvider;

  @Value("${security.jwtUpdateInMs}")
  private int jstUpdateInMs;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    try {
      String jwtToken = jwtTokenProvider.getJwtTokenFromHttepRequest(request);

      if (StringUtils.hasText(jwtToken)) {
        Optional<Claims> claimsOptional = jwtTokenProvider.validateToken(jwtToken);

        if (claimsOptional.isPresent()) {
          Claims claims = claimsOptional.get();

          Authentication authentication = jwtTokenProvider.getAuthentication(claims);
          SecurityContextHolder.getContext().setAuthentication(authentication);

          Date expiredDate = claims.getExpiration();
          Date now = new Date();
          Date date24 = new Date(now.getTime() + jstUpdateInMs);

          if (expiredDate.before(date24)) {
            response.addHeader(
                "Update-Auth-Token", jwtTokenProvider.generateJwtToken(authentication));
          }
        }
      }

    } catch (Exception e) {
      throw new AppException("Authentaction Filter에서 에러");
    }

    filterChain.doFilter(request, response);
  }
}

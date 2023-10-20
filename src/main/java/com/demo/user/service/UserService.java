package com.demo.user.service;

import com.demo.security.JwtAuthenticationResponse;
import com.demo.user.payload.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
  ResponseEntity<JwtAuthenticationResponse> authenticateUser(
      LoginRequest loginRequest, HttpServletRequest httpServletRequest);
}

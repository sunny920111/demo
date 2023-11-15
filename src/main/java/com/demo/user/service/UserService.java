package com.demo.user.service;

import com.demo.common.payload.ApiResponse;
import com.demo.security.JwtAuthenticationResponse;
import com.demo.user.payload.LoginRequest;
import com.demo.user.payload.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
  ResponseEntity<JwtAuthenticationResponse> signIn(
      LoginRequest loginRequest, HttpServletRequest httpServletRequest);

  ApiResponse signUp(RegisterRequest registerRequest);
}

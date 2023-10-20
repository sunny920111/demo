package com.demo.user.controller;

import com.demo.security.JwtAuthenticationResponse;
import com.demo.user.payload.LoginRequest;
import com.demo.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final UserService userService;

  @PostMapping("/signIn")
  public ResponseEntity<JwtAuthenticationResponse> signIn(
      @Valid @RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest) {
    return this.userService.authenticateUser(loginRequest, httpServletRequest);
  }
}

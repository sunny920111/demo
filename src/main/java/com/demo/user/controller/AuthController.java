package com.demo.user.controller;

import com.demo.common.payload.ApiResponse;
import com.demo.security.JwtAuthenticationResponse;
import com.demo.user.payload.LoginRequest;
import com.demo.user.payload.RegisterRequest;
import com.demo.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final UserService userService;

  @PostMapping("/signIn")
  public ResponseEntity<JwtAuthenticationResponse> signIn(
      @Valid @RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest) {
    return this.userService.signIn(loginRequest, httpServletRequest);
  }

  @PostMapping("/signUp")
  public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody RegisterRequest registerRequest) {
    return ResponseEntity.ok(this.userService.signUp(registerRequest));
  }
}

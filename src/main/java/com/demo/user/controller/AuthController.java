package com.demo.user.controller;

import com.demo.common.payload.ApiResponse;
import com.demo.security.JwtAuthenticationResponse;
import com.demo.security.PrincipalUtil;
import com.demo.security.UserPrincipal;
import com.demo.user.converter.UserConverter;
import com.demo.user.payload.LoginRequest;
import com.demo.user.payload.RegisterRequest;
import com.demo.user.payload.UserDetailPayload;
import com.demo.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

  private final UserService userService;

  private final UserDetailsService userDetailsService;

  private final UserConverter userConverter;

  @PostMapping("/signIn")
  public ResponseEntity<JwtAuthenticationResponse> signIn(
      @Valid @RequestBody LoginRequest loginRequest, HttpServletRequest httpServletRequest) {
    return this.userService.signIn(loginRequest, httpServletRequest);
  }

  @PostMapping("/signUp")
  public ResponseEntity<ApiResponse> signUp(@Valid @RequestBody RegisterRequest registerRequest) {
    return ResponseEntity.ok(this.userService.signUp(registerRequest));
  }

  @GetMapping("/detail")
  public ResponseEntity<UserDetailPayload> getUserDetail(Principal principal) {
    if (principal == null) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    UserDetails userDetail =
        this.userDetailsService.loadUserByUsername(PrincipalUtil.getEmail(principal));

    if (userDetail instanceof UserPrincipal userPrincipal) {
      UserDetailPayload detailPayload = userConverter.toDetailPayload(userPrincipal);
      return ResponseEntity.ok(detailPayload);
    }

    return ResponseEntity.notFound().build();
  }
}

package com.demo.user.service.impl;

import com.demo.common.exception.AppException;
import com.demo.security.JwtAuthenticationResponse;
import com.demo.security.JwtTokenProvider;
import com.demo.user.converter.UserConverter;
import com.demo.user.entity.User;
import com.demo.user.payload.LoginRequest;
import com.demo.user.repository.UserRepository;
import com.demo.user.service.UserService;

import java.time.OffsetDateTime;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserConverter userConverter;

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  @Override
  public ResponseEntity<JwtAuthenticationResponse> authenticateUser(
      LoginRequest loginRequest, HttpServletRequest httpServletRequest) {

    String email = loginRequest.getEmail();
    String password = loginRequest.getPassword();

    Optional<User> userOptional = userRepository.findByEmail(email);

    if (!userOptional.isPresent()) {
      throw new AppException("e-mail이 존재 하지 않습니다.");
    }

    if (!userOptional.get().getPassword().equals(password)) {
      throw new AppException("비밀번호가 일치하지 않습니다.");
    }

    try {
      Authentication authentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(email, password));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwtToken = jwtTokenProvider.generateJwtToken(authentication);

      return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken, false));

    } catch (Exception e) {
      throw new AppException("Authentication 쪽 에러");
    }
  }
}

package com.demo.user.service;

import com.demo.common.payload.ApiResponse;
import com.demo.security.JwtAuthenticationResponse;
import com.demo.user.payload.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UserService {
  ResponseEntity<JwtAuthenticationResponse> signIn(
      LoginRequest loginRequest, HttpServletRequest httpServletRequest);

  ApiResponse signUp(RegisterRequest registerRequest);

  List<SemesterSummary> getSemesterList(UserSemesterRequest userSemesterRequest);

  Page<UserSummary> getUserSummaryPageList(UserSearchRequest userSearchRequest, Pageable pageable);

  UserSummary getUserSummary(String userId);

  ApiResponse updateUser(String userId, UserRequest userRequest);
}

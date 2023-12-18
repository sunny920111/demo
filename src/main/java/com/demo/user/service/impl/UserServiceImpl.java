package com.demo.user.service.impl;

import com.demo.common.exception.AppException;
import com.demo.common.payload.ApiResponse;
import com.demo.security.JwtAuthenticationResponse;
import com.demo.security.JwtTokenProvider;
import com.demo.user.converter.UserConverter;
import com.demo.user.entity.SemesterInfo;
import com.demo.user.entity.User;
import com.demo.user.entity.UserRole;
import com.demo.user.entity.UserSemester;
import com.demo.user.payload.LoginRequest;
import com.demo.user.payload.RegisterRequest;
import com.demo.user.payload.SemesterSummary;
import com.demo.user.payload.UserSemesterRequest;
import com.demo.user.repository.SemesterRepository;
import com.demo.user.repository.UserRepository;
import com.demo.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final SemesterRepository semesterRepository;

  private final UserConverter userConverter;

  private final AuthenticationManager authenticationManager;
  private final JwtTokenProvider jwtTokenProvider;

  private final PasswordEncoder passwordEncoder;
  private final int PASSWORD_ERROR_MAX_COUNT = 5;

  @Override
  public ResponseEntity<JwtAuthenticationResponse> signIn(
      LoginRequest loginRequest, HttpServletRequest httpServletRequest) {

    String email = loginRequest.getEmail();
    String password = loginRequest.getPassword();

    Optional<User> userOptional = userRepository.findByEmail(email);

    if (!userOptional.isPresent()) {
      throw new AppException("e-mail이 존재 하지 않습니다.");
    }

    User loginUser = userOptional.get();

    try {
      Authentication authentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(email, password));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwtToken = jwtTokenProvider.generateJwtToken(authentication);

      loginUser.setPasswordErrorCnt(0);

      Boolean isExpired =
          !OffsetDateTime.now().isBefore(loginUser.getLastPwChangeDatetime().plusMonths(3));
      return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken, false));

    } catch (Exception e) {
      throw new AppException("Authentication 쪽 에러");
    }
  }

  @Override
  @Transactional
  public ApiResponse signUp(RegisterRequest registerRequest) {

    Optional<User> userOptional = userRepository.findByEmail(registerRequest.getEmail());

    if (userOptional.isPresent()) {
      return new ApiResponse(false, "이미 등록된 이메일 주소입니다.");
    }

    if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword())) {
      return new ApiResponse(false, "비밀번호가 서로 일치하지 않습니다.");
    }

    User newUser = userConverter.toUser(registerRequest);
    newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
    newUser.setUserRoles(new ArrayList<>());
    User savedUser = userRepository.save(newUser);

    UserRole userRole = new UserRole();
    userRole.setUserId(newUser.getUserId());
    userRole.setRoleId("ROLE_STUDENT");

    savedUser.getUserRoles().add(userRole);

    return new ApiResponse(true, "등록에 성공했습니다.");
  }

  @Override
  public List<SemesterSummary> getSemesterList(UserSemesterRequest userSemesterRequest) {

    List<SemesterSummary> semesterSummaryList = new ArrayList<>();
    Optional<User> userOptional = userRepository.findById(userSemesterRequest.getUserId());

    if (userOptional.isPresent()) {

      if (userOptional.get().getUserRoles().stream()
          .anyMatch(x -> x.getRoleId().equals(userSemesterRequest.getType()))) {
        List<SemesterInfo> semesterInfoList =
            semesterRepository.findAllByRoleId(userSemesterRequest.getType());

        semesterSummaryList = userConverter.toSemesterSumamryList(semesterInfoList);

      } else {
        List<UserSemester> userSemesterList = userOptional.get().getUserSemesterSummaries();
        List<SemesterInfo> semesterInfoList =
            userSemesterList.stream()
                .filter(x -> x.getSemesterInfo().getRoleId().equals(userSemesterRequest.getType()))
                .map(UserSemester::getSemesterInfo)
                .collect(Collectors.toList());
        semesterSummaryList = userConverter.toSemesterSumamryList(semesterInfoList);
      }
    }

    return semesterSummaryList;
  }
}

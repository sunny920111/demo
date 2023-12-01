package com.demo.security;

import com.demo.common.exception.AppException;
import com.demo.user.entity.User;
import com.demo.user.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
  UserRepository userRepository;
  HttpServletRequest httpServletRequest;

  public CustomUserDetailService(
      UserRepository userRepository, HttpServletRequest httpServletRequest) {
    super();
    this.userRepository = userRepository;
    this.httpServletRequest = httpServletRequest;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    if (StringUtils.isEmpty(email)) {
      throw new AppException("Email is empty.");
    }

    User user =
        userRepository.findByEmail(email).orElseThrow(() -> new AppException("등록된 Email이 없습니다. "));

    return UserPrincipal.create(user);
  }

  @Transactional
  public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
    if (StringUtils.isEmpty(userId)) {
      throw new AppException("userId is empty.");
    }

    User user =
        userRepository.findById(userId).orElseThrow(() -> new AppException("등록된 userId가 없습니다. "));

    return UserPrincipal.create(user);
  }
}

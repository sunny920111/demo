package com.demo.user.service.impl;

import com.demo.user.converter.UserConverter;
import com.demo.user.entity.User;
import com.demo.user.payload.UserSearchRequest;
import com.demo.user.payload.UserSummary;
import com.demo.user.repository.UserRepository;
import com.demo.user.service.UserService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserConverter userConverter;

  @Override
  public List<UserSummary> getUserList(UserSearchRequest searchRequest) {
    List<User> userList = this.userRepository.getUserList();

    List<UserSummary> summaryList = new ArrayList<>();

    userList.forEach(
        user -> {
          summaryList.add(this.userConverter.toSummary(user));
        });

    return summaryList;
  }

  @Override
  public Boolean signIn(UserSearchRequest searchRequest) {

    return this.userRepository.existsByEmailAndPassword(
        searchRequest.getEmail(), searchRequest.getPassword());
  }
}

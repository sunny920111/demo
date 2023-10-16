package com.demo.user.service;

import com.demo.user.payload.UserSearchRequest;
import com.demo.user.payload.UserSummary;
import java.util.List;

public interface UserService {
  List<UserSummary> getUserList(UserSearchRequest searchRequest);

  Boolean signIn(UserSearchRequest searchRequest);
}

package com.demo.user.repository;

import com.demo.user.entity.User;
import java.util.List;

public interface UserRepositoryQueryDsl {
  List<User> getUserList();
}

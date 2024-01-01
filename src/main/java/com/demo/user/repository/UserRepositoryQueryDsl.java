package com.demo.user.repository;

import com.demo.user.entity.User;
import com.demo.user.payload.UserSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryQueryDsl {
  Page<User> search(UserSearchRequest userSearchRequest, Pageable pageable);
}

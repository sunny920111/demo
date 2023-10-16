package com.demo.user.repository;

import com.demo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>, UserRepositoryQueryDsl {

  Boolean existsByEmailAndPassword(String email, String password);
}

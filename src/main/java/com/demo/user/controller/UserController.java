package com.demo.user.controller;

import com.demo.user.payload.UserSearchRequest;
import com.demo.user.payload.UserSummary;
import com.demo.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<UserSummary>> getUserList(UserSearchRequest searchRequest) {
    return ResponseEntity.ok(this.userService.getUserList(searchRequest));
  }
}

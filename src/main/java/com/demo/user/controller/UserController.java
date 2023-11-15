package com.demo.user.controller;

import com.demo.common.payload.ApiResponse;
import com.demo.user.payload.LoginRequest;
import com.demo.user.payload.RegisterRequest;
import com.demo.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;
}

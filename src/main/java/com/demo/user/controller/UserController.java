package com.demo.user.controller;

import com.demo.common.payload.ApiResponse;
import com.demo.user.payload.*;
import com.demo.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<Page<UserSummary>> getUserSummaryPageList(
      UserSearchRequest userSearchRequest, Pageable pageable) {
    Page<UserSummary> pages = userService.getUserSummaryPageList(userSearchRequest, pageable);
    return ResponseEntity.ok(pages);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserSummary> getUserSummary(@PathVariable String userId) {
    UserSummary userSummary = userService.getUserSummary(userId);
    return ResponseEntity.ok(userSummary);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<ApiResponse> updateUser(
      @PathVariable String userId, @RequestBody UserRequest userRequest) {
    return ResponseEntity.ok(userService.updateUser(userId, userRequest));
  }

  @GetMapping("/semester")
  public ResponseEntity<List<SemesterSummary>> getSemesterInfo(
      UserSemesterRequest userSemesterRequest) {
    List<SemesterSummary> semesterSummaryList = userService.getSemesterList(userSemesterRequest);
    return ResponseEntity.ok(semesterSummaryList);
  }
}

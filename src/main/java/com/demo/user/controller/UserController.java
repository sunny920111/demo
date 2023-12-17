package com.demo.user.controller;

import com.demo.user.payload.SemesterSummary;
import com.demo.user.payload.UserSemesterRequest;
import com.demo.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @GetMapping("/semester")
  public ResponseEntity<List<SemesterSummary>> getSemesterInfo(
      UserSemesterRequest userSemesterRequest) {
    List<SemesterSummary> semesterSummaryList = userService.getSemesterList(userSemesterRequest);
    return ResponseEntity.ok(semesterSummaryList);
  }
}

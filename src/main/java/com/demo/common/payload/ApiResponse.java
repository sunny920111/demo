package com.demo.common.payload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ApiResponse {

  private Boolean success;
  private String message;

  public ApiResponse(boolean success, String message) {
    this.success = success;
    this.message = message;
  }
}

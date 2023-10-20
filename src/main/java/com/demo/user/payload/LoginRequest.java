package com.demo.user.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
  @NotBlank(message = "Email을 입력해주세요.")
  private String email;

  @NotBlank(message = "패스워드를 입력해주세요.")
  private String password;
}

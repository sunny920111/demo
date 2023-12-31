package com.demo.user.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchRequest {
  private String name;
  private String email;
  private String password;
}

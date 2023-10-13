package com.demo.user.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchRequest {
  private String name;
  private String email;
}

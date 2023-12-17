package com.demo.user.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSemesterRequest {
  private String userId;
  private String type;
}

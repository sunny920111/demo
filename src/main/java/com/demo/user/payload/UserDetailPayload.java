package com.demo.user.payload;

import com.demo.security.CustomGrantedAuthority;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailPayload {
  private String userId;
  private String email;
  private String username;
  private List<CustomGrantedAuthority> authorities;
}

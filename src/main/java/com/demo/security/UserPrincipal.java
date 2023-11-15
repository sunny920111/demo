package com.demo.security;

import com.demo.user.entity.User;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {
  @Getter @Setter private String userId;
  private String name;

  @Getter @Setter private String email;
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(
      String userId,
      String name,
      String email,
      String password,
      List<CustomGrantedAuthority> authorities) {
    super();
    this.userId = userId;
    this.name = name;
    this.email = email;
    this.password = password;
    this.authorities = authorities;
  }

  public static UserPrincipal create(User user) {
    List<CustomGrantedAuthority> authorities =
        user.getUserRoles().stream()
            .map(
                userRole ->
                    new CustomGrantedAuthority(
                        userRole.getRoleId(), userRole.getRole().getDescription()))
            .collect(Collectors.toList());

    return new UserPrincipal(
        user.getUserId(), user.getName(), user.getEmail(), user.getPassword(), authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

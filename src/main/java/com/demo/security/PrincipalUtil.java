package com.demo.security;

import java.security.Principal;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PrincipalUtil {
  private PrincipalUtil() {}

  public static String getEmail(Principal principal) {
    Optional<UserPrincipal> optionalUserPrincipal = getUserPrinciple(principal);
    if (optionalUserPrincipal.isPresent()) {
      return optionalUserPrincipal.get().getEmail();
    }
    return "";
  }

  public static Optional<UserPrincipal> getUserPrinciple(Principal principal) {
    if (principal instanceof UsernamePasswordAuthenticationToken) {
      UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
      Object tokenPrinciple = token.getPrincipal();
      if (tokenPrinciple instanceof UserPrincipal) {
        UserPrincipal userPrincipal = (UserPrincipal) tokenPrinciple;
        return Optional.of(userPrincipal);
      }
    }
    return Optional.empty();
  }
}

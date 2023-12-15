package com.demo.user.converter;

import com.demo.security.UserPrincipal;
import com.demo.user.entity.User;
import com.demo.user.payload.RegisterRequest;
import com.demo.user.payload.UserDetailPayload;
import com.demo.user.payload.UserSummary;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter {
  UserSummary toSummary(User user);

  User toUser(RegisterRequest registerRequest);

  @Mapping(target = "authorities", ignore = true)
  UserDetailPayload toDetailPayload(UserPrincipal user);
}

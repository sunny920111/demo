package com.demo.user.entity;

import com.demo.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

@Entity
@Table(name = "tn_cm_user")
@Getter
@Setter
@DynamicUpdate
public class User extends BaseEntity {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "user_id", columnDefinition = "VARCHAR(255)")
  String userId;

  String name;

  String password;

  String email;

  String lastLoginIp;

  OffsetDateTime lastLoginDatetime;

  int passwordErrorCnt;

  @CreationTimestamp OffsetDateTime lastPwChangeDatetime;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("roleId asc ")
  List<UserRole> userRoles = new ArrayList<>();

  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("id asc ")
  List<UserSemester> userSemesterSummaries = new ArrayList<>();
}

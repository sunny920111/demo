package com.demo.user.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

@Entity
@Table(name = "tn_cm_user")
@Getter
@Setter
public class User implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid")
  String userId;

  String name;

  String password;

  String email;

  String lastLoginIp;

  OffsetDateTime lastLoginDatetime;

  int passwordErrorCnt;

  @CreationTimestamp OffsetDateTime lastPwChangeDatetime;

  @CreationTimestamp OffsetDateTime regDatetime;

  String regId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "regId", insertable = false, updatable = false)
  User regUser;

  @UpdateTimestamp OffsetDateTime modDatetime;

  String modId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "modId", insertable = false, updatable = false)
  User modUser;

  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("roleId asc ")
  List<UserRole> userRoles = new ArrayList<>();
}

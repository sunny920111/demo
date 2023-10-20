package com.demo.user.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "tn_cm_user_role")
@Getter
@Setter
public class UserRole {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String userId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId", insertable = false, updatable = false)
  private User user;

  private String roleId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "roleId", insertable = false, updatable = false)
  private Role role;

  @CreationTimestamp LocalDateTime regDatetime;

  String regId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "regId", insertable = false, updatable = false)
  User regUser;

  @UpdateTimestamp LocalDateTime modDatetime;

  String modId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "modId", insertable = false, updatable = false)
  User modUser;
}

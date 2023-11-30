package com.demo.user.entity;

import com.demo.common.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "tn_cm_user_role")
@Getter
@Setter
public class UserRole extends BaseEntity {

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
}

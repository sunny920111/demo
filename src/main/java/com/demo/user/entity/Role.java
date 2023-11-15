package com.demo.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "tn_cm_role")
@Getter
@Setter
public class Role {

  @Id private String roleId;

  private String description;

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
}

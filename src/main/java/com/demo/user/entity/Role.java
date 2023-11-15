package com.demo.user.entity;

import com.demo.common.entity.BaseEntity;
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
public class Role extends BaseEntity {

  @Id private String roleId;

  private String description;

  @CreationTimestamp OffsetDateTime regDatetime;
}

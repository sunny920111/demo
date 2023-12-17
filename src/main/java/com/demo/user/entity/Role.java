package com.demo.user.entity;

import com.demo.common.entity.BaseEntity;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "tn_cm_role")
@Getter
@Setter
public class Role extends BaseEntity {

  @Id private String roleId;

  private String description;
}

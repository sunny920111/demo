package com.demo.common.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

  @CreationTimestamp OffsetDateTime regDatetime;
  @CreatedBy String regId;
  @UpdateTimestamp OffsetDateTime modDatetime;
  @LastModifiedBy String modId;
}

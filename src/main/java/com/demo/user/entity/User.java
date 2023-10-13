package com.demo.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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

  LocalDateTime regDatetime;

  String regUserId;

  LocalDateTime modDatetime;

  String modUserId;
}

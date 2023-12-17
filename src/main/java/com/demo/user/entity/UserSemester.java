package com.demo.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tn_cm_user_semester")
@Getter
@Setter
public class UserSemester {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String userId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId", insertable = false, updatable = false)
  private User user;

  private long semesterId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "semesterId",
      referencedColumnName = "id",
      insertable = false,
      updatable = false)
  private SemesterInfo semesterInfo;
}

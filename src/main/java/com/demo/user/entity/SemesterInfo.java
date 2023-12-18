package com.demo.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tn_cm_semester_info")
@Getter
@Setter
public class SemesterInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String roleId;
  private int lectureYear;
  private int lectureSemester;
  private String description;
}

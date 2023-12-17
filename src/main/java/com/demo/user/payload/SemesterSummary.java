package com.demo.user.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SemesterSummary {
  private int id;
  private String roleId;
  private int lectureYear;
  private int lectureSemester;

  private String getLectureType() {
    return "[" + lectureYear + "-" + lectureSemester + "]";
  }
}

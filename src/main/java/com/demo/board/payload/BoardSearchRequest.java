package com.demo.board.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchRequest {
  private String type;
  private String title;
  private String regId;
}

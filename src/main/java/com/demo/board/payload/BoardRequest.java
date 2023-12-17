package com.demo.board.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequest {
  private long boardId;
  private String type;
  private long semesterId;
  private String title;
  private String content;
  private String noticeYn;
  private String privateYn;
}

package com.demo.board.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
  private String content;
  private long boardId;
}

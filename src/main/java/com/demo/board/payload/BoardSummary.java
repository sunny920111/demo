package com.demo.board.payload;

import com.demo.user.payload.UserSummary;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSummary {
  private long boardId;
  private String type;
  private String title;
  private String content;
  private UserSummary regUser;
  private OffsetDateTime regDatetime;
}

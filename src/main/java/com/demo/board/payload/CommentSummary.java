package com.demo.board.payload;

import com.demo.user.payload.UserSummary;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSummary {
  private long id;
  private String content;
  private UserSummary modUser;
  private OffsetDateTime modDatetime;
}

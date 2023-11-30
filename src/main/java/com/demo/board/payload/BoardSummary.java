package com.demo.board.payload;

import com.demo.user.payload.UserSummary;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSummary {
  private String title;
  private String content;
  private UserSummary modeUser;
  private OffsetDateTime modDatetime;
}

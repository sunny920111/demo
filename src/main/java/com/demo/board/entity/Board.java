package com.demo.board.entity;

import com.demo.common.entity.BaseEntity;
import com.demo.user.entity.User;
import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tn_cm_board")
@Getter
@Setter
public class Board extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long boardId;

  private String type;

  private long semesterId;

  private String title;

  private String content;

  private int recommendCnt;

  private int inquiryCnt;

  private String noticeYn;

  private String privateYn;

  private String delYn;

  @OneToMany(mappedBy = "board")
  private List<Comment> commentList;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "regId", insertable = false, updatable = false)
  private User regUser;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "modId", insertable = false, updatable = false)
  private User modUser;
}

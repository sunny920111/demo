package com.demo.board.repository;

import com.demo.board.entity.Board;
import com.demo.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class BoardRepositoryQueryDslImpl extends QuerydslRepositorySupport
    implements BoardRepositoryQueryDsl {

  private final JPAQueryFactory queryFactory;

  public BoardRepositoryQueryDslImpl(JPAQueryFactory queryFactory) {
    super(Board.class);
    this.queryFactory = queryFactory;
  }
}

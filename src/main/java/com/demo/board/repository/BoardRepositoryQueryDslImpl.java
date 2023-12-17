package com.demo.board.repository;

import static com.demo.board.entity.QBoard.board;

import com.demo.board.entity.Board;
import com.demo.board.payload.BoardSearchRequest;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

public class BoardRepositoryQueryDslImpl extends QuerydslRepositorySupport
    implements BoardRepositoryQueryDsl {

  private final JPAQueryFactory queryFactory;

  public BoardRepositoryQueryDslImpl(JPAQueryFactory queryFactory) {
    super(Board.class);
    this.queryFactory = queryFactory;
  }

  @Override
  public Page<Board> search(BoardSearchRequest boardSearchRequest, Pageable pageable) {

    JPQLQuery<Board> query = from(board);

    query.where(board.type.eq(boardSearchRequest.getType()));
    query.where(board.semesterId.longValue().eq(boardSearchRequest.getSemesterId()));

    if (StringUtils.hasText(boardSearchRequest.getTitle())) {
      query.where(board.title.contains(boardSearchRequest.getTitle()));
    }
    if (StringUtils.hasText(boardSearchRequest.getRegName())) {
      query.where(board.regUser.name.contains(boardSearchRequest.getRegName()));
    }

    query.where(board.delYn.eq("N"));

    query.orderBy(board.regDatetime.desc(), board.title.asc());

    List<Board> boardList = getQuerydsl().applyPagination(pageable, query).fetch();
    return new PageImpl<>(boardList, pageable, query.fetchCount());
  }
}

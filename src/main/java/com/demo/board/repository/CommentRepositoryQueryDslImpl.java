package com.demo.board.repository;

import static com.demo.board.entity.QComment.comment;

import com.demo.board.entity.Comment;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CommentRepositoryQueryDslImpl extends QuerydslRepositorySupport
    implements CommentRepositoryQueryDsl {

  private final JPAQueryFactory queryFactory;

  public CommentRepositoryQueryDslImpl(JPAQueryFactory queryFactory) {
    super(Comment.class);
    this.queryFactory = queryFactory;
  }

  @Override
  public Page<Comment> search(Pageable pageable) {

    JPQLQuery<Comment> query = from(comment);

    query.where(comment.delYn.eq("N"));
    query.orderBy(comment.regDatetime.desc());

    List<Comment> commentList = getQuerydsl().applyPagination(pageable, query).fetch();
    return new PageImpl<>(commentList, pageable, query.fetchCount());
  }
}

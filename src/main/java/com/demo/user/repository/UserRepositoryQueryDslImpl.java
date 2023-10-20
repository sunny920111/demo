package com.demo.user.repository;

import static com.demo.user.entity.QUser.user;

import com.demo.user.entity.User;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryQueryDslImpl extends QuerydslRepositorySupport
    implements UserRepositoryQueryDsl {

  private final JPAQueryFactory queryFactory;

  public UserRepositoryQueryDslImpl(JPAQueryFactory queryFactory) {
    super(User.class);
    this.queryFactory = queryFactory;
  }
}

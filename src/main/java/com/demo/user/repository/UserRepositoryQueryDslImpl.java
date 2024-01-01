package com.demo.user.repository;

import static com.demo.user.entity.QUser.user;

import com.demo.user.entity.User;
import com.demo.user.payload.UserSearchRequest;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class UserRepositoryQueryDslImpl extends QuerydslRepositorySupport
    implements UserRepositoryQueryDsl {

  private final JPAQueryFactory queryFactory;

  public UserRepositoryQueryDslImpl(JPAQueryFactory queryFactory) {
    super(User.class);
    this.queryFactory = queryFactory;
  }

  @Override
  public Page<User> search(UserSearchRequest userSearchRequest, Pageable pageable) {
    JPQLQuery<User> query = from(user);

    if (StringUtils.hasText(userSearchRequest.getName())) {
      query.where(user.name.contains(userSearchRequest.getName()).or(user.email.contains(userSearchRequest.getName())));
    }

    query.orderBy(user.regDatetime.desc(), user.name.asc());

    List<User> userList = getQuerydsl().applyPagination(pageable, query).fetch();
    return new PageImpl<>(userList, pageable, query.fetchCount());
  }
}

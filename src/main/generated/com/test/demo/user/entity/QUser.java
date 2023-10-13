package com.neuro.demo.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1595325986L;

    public static final QUser user = new QUser("user");

    public final StringPath email = createString("email");

    public final DateTimePath<java.time.LocalDateTime> modDatetime = createDateTime("modDatetime", java.time.LocalDateTime.class);

    public final StringPath modUserId = createString("modUserId");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final DateTimePath<java.time.LocalDateTime> regDatetime = createDateTime("regDatetime", java.time.LocalDateTime.class);

    public final StringPath regUserId = createString("regUserId");

    public final StringPath userId = createString("userId");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}


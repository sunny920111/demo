package com.demo.board.repository;

import com.demo.board.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepositoryQueryDsl {

  Page<Comment> search(Pageable pageable);
}

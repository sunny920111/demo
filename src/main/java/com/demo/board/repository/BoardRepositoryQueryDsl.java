package com.demo.board.repository;

import com.demo.board.entity.Board;
import com.demo.board.payload.BoardSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryQueryDsl {
  Page<Board> search(BoardSearchRequest boardSearchRequest, Pageable pageable);
}

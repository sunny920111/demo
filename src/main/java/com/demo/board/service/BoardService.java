package com.demo.board.service;

import com.demo.board.payload.BoardRequest;
import com.demo.board.payload.BoardSearchRequest;
import com.demo.board.payload.BoardSummary;
import com.demo.common.payload.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
  Page<BoardSummary> getBoardsummary(BoardSearchRequest boardSearchRequest, Pageable pageable);

  ApiResponse saveBoard(BoardRequest boardRequest);

  ApiResponse updateBoard(BoardRequest boardRequest);

  ApiResponse deleteBoard(long boardId);
}

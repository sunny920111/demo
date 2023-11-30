package com.demo.board.controller;

import com.demo.board.payload.BoardRequest;
import com.demo.board.payload.BoardSearchRequest;
import com.demo.board.payload.BoardSummary;
import com.demo.board.service.BoardService;
import com.demo.common.payload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BorderController {

  private BoardService boardService;

  @GetMapping
  public ResponseEntity<Page<BoardSummary>> getBoardSummary(
      BoardSearchRequest boardSearchRequest, Pageable pageable) {
    Page<BoardSummary> pages = boardService.getBoardsummary(boardSearchRequest, pageable);
    return ResponseEntity.ok(pages);
  }

  @PostMapping
  public ResponseEntity<ApiResponse> saveBoard(BoardRequest boardRequest) {
    return ResponseEntity.ok(boardService.saveBoard(boardRequest));
  }

  @PutMapping("/{boardId}")
  public ResponseEntity<ApiResponse> updateBoard(BoardRequest boardRequest) {
    return ResponseEntity.ok(boardService.updateBoard(boardRequest));
  }

  @DeleteMapping("/{boardId}")
  public ResponseEntity<ApiResponse> deleteBoard(@PathVariable long boardId) {
    return ResponseEntity.ok(boardService.deleteBoard(boardId));
  }
}
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
public class BoardController {

  private final BoardService boardService;

  @GetMapping
  public ResponseEntity<Page<BoardSummary>> getBoardSummaryList(
      BoardSearchRequest boardSearchRequest, Pageable pageable) {
    Page<BoardSummary> pages = boardService.getBoardSummaryList(boardSearchRequest, pageable);
    return ResponseEntity.ok(pages);
  }

  @GetMapping("/{boardId}")
  public ResponseEntity<BoardSummary> getBoardSummary(@PathVariable long boardId) {
    BoardSummary boardSummary = boardService.getBoardSummary(boardId);
    return ResponseEntity.ok(boardSummary);
  }

  @PostMapping
  public ResponseEntity<ApiResponse> saveBoard(@RequestBody BoardRequest boardRequest) {
    return ResponseEntity.ok(boardService.saveBoard(boardRequest));
  }

  @PutMapping("/{boardId}")
  public ResponseEntity<ApiResponse> updateBoard(@RequestBody BoardRequest boardRequest) {
    return ResponseEntity.ok(boardService.updateBoard(boardRequest));
  }

  @DeleteMapping("/{boardId}")
  public ResponseEntity<ApiResponse> deleteBoard(@PathVariable long boardId) {
    return ResponseEntity.ok(boardService.deleteBoard(boardId));
  }
}

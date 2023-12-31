package com.demo.board.controller;

import com.demo.board.payload.*;
import com.demo.board.service.CommentService;
import com.demo.common.payload.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
  private final CommentService commentService;

  @GetMapping
  public ResponseEntity<Page<CommentSummary>> getCommentSummaryPageList(Pageable pageable) {
    Page<CommentSummary> pages = commentService.getCommentSummaryPageList(pageable);
    return ResponseEntity.ok(pages);
  }

  @GetMapping("/boardId/{boardId}")
  public ResponseEntity<List<CommentSummary>> getCommentSummaryList(@PathVariable long boardId) {
    List<CommentSummary> list = commentService.getCommentSummaryList(boardId);
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{commentId}")
  public ResponseEntity<CommentSummary> getCommentSummary(@PathVariable long commentId) {
    CommentSummary boardSummary = commentService.getCommentSummary(commentId);
    return ResponseEntity.ok(boardSummary);
  }

  @PostMapping
  public ResponseEntity<ApiResponse> saveComment(@RequestBody CommentRequest commentRequest) {
    return ResponseEntity.ok(commentService.saveComment(commentRequest));
  }

  @PutMapping("/{commentId}")
  public ResponseEntity<ApiResponse> updateComment(
      @PathVariable long commentId, @RequestBody CommentRequest commentRequest) {
    return ResponseEntity.ok(commentService.updateComment(commentId, commentRequest));
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<ApiResponse> deleteComment(@PathVariable long commentId) {
    return ResponseEntity.ok(commentService.deleteComment(commentId));
  }
}

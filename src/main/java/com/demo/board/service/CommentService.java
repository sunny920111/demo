package com.demo.board.service;

import com.demo.board.payload.CommentRequest;
import com.demo.board.payload.CommentSummary;
import com.demo.common.payload.ApiResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

  Page<CommentSummary> getCommentSummaryPageList(Pageable pageable);

  List<CommentSummary> getCommentSummaryList(long boardId);

  CommentSummary getCommentSummary(long commentId);

  ApiResponse saveComment(CommentRequest commentRequest);

  ApiResponse updateComment(long commentId, CommentRequest commentRequest);

  ApiResponse deleteComment(long commentId);
}

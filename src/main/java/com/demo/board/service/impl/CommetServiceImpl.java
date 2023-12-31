package com.demo.board.service.impl;

import com.demo.board.converter.CommentConverter;
import com.demo.board.entity.Comment;
import com.demo.board.payload.CommentRequest;
import com.demo.board.payload.CommentSummary;
import com.demo.board.repository.CommentRepository;
import com.demo.board.service.CommentService;
import com.demo.common.exception.AppException;
import com.demo.common.payload.ApiResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommetServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final CommentConverter commentConverter;

  @Override
  public Page<CommentSummary> getCommentSummaryPageList(Pageable pageable) {
    Page<Comment> pages = commentRepository.search(pageable);
    return pages.map(commentConverter::toSummary);
  }

  @Override
  public List<CommentSummary> getCommentSummaryList(long boardId) {
    List<Comment> list = commentRepository.findAllByBoardIdAndDelYn(boardId, "N");
    return list.stream().map(commentConverter::toSummary).collect(Collectors.toList());
  }

  @Override
  public CommentSummary getCommentSummary(long commentId) {

    Optional<Comment> comment = commentRepository.findById(commentId);

    if (!comment.isPresent()) {
      throw new AppException(commentId + " 존재하지 않는 코멘트입니다.");
    }

    return commentConverter.toSummary(comment.get());
  }

  @Override
  public ApiResponse saveComment(CommentRequest commentRequest) {

    Comment comment = commentConverter.toComment(commentRequest);
    comment.setDelYn("N");
    commentRepository.save(comment);

    return new ApiResponse(true, comment.getId() + " 성공적으로 등록했습니다.");
  }

  @Override
  @Transactional
  public ApiResponse updateComment(long commentId, CommentRequest commentRequest) {

    Optional<Comment> comment = commentRepository.findById(commentId);

    if (!comment.isPresent()) {
      return new ApiResponse(false, comment.get().getId() + "해당 댓글이 존재하지 않습니다.");
    } else {
      comment.get().setContent(commentRequest.getContent());
      return new ApiResponse(true, comment.get().getId() + "성공적으로 등록했습니다.");
    }
  }

  @Override
  @Transactional
  public ApiResponse deleteComment(long commentId) {

    Optional<Comment> comment = commentRepository.findById(commentId);
    if (!comment.isPresent()) {
      return new ApiResponse(false, comment.get().getId() + "해당 댓글이 존재하지 않습니다.");
    } else {
      comment.get().setDelYn("Y");
      return new ApiResponse(true, comment.get().getId() + "성공적으로 삭제했습니다.");
    }
  }
}

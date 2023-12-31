package com.demo.board.converter;

import com.demo.board.entity.Comment;
import com.demo.board.payload.CommentRequest;
import com.demo.board.payload.CommentSummary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentConverter {

  public CommentSummary toSummary(Comment comment);

  Comment toComment(CommentRequest commentRequest);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "boardId", ignore = true)
  void requestToComment(CommentRequest commentRequest, @MappingTarget Comment comment);
}

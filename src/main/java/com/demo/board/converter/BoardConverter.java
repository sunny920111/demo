package com.demo.board.converter;

import com.demo.board.entity.Board;
import com.demo.board.payload.BoardRequest;
import com.demo.board.payload.BoardSummary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardConverter {
  public BoardSummary toSummary(Board board);

  Board toBoard(BoardRequest boardRequest);

  @Mapping(target = "boardId", ignore = true)
  void requestToBoard(BoardRequest boardRequest, @MappingTarget Board board);
}

package com.demo.board.service.impl;

import com.demo.board.converter.BoardConverter;
import com.demo.board.entity.Board;
import com.demo.board.payload.BoardRequest;
import com.demo.board.payload.BoardSearchRequest;
import com.demo.board.payload.BoardSummary;
import com.demo.board.repository.BoardRepository;
import com.demo.board.service.BoardService;
import com.demo.common.exception.AppException;
import com.demo.common.payload.ApiResponse;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

  private final BoardRepository boardRepository;
  private final BoardConverter boardConverter;

  @Override
  public Page<BoardSummary> getBoardSummaryList(
      BoardSearchRequest boardSearchRequest, Pageable pageable) {

    Page<Board> pages = boardRepository.search(boardSearchRequest, pageable);

    return pages.map(boardConverter::toSummary);
  }

  @Override
  public BoardSummary getBoardSummary(long boardId) {

    Optional<Board> board = boardRepository.findById(boardId);

    if (!board.isPresent()) {
      throw new AppException(boardId + " 존재하지 않는 게시글입니다.");
    }
    return boardConverter.toSummary(board.get());
  }

  @Override
  public ApiResponse saveBoard(BoardRequest boardRequest) {

    Board board = boardConverter.toBoard(boardRequest);
    board.setDelYn("N");
    boardRepository.save(board);

    return new ApiResponse(true, board.getBoardId() + " 성공적으로 등록했습니다.");
  }

  @Transactional
  @Override
  public ApiResponse updateBoard(BoardRequest boardRequest) {
    Optional<Board> board = boardRepository.findById(boardRequest.getBoardId());
    if (!board.isPresent()) {
      return new ApiResponse(false, board.get().getBoardId() + "해당 게시글이 존재하지 않습니다.");
    } else {
      boardConverter.requestToBoard(boardRequest, board.get());
      return new ApiResponse(true, board.get().getBoardId() + "성공적으로 등록했습니다.");
    }
  }

  @Transactional
  @Override
  public ApiResponse deleteBoard(long boardId) {
    // boardRepository.deleteById(boardId);
    Optional<Board> board = boardRepository.findById(boardId);
    if (!board.isPresent()) {
      return new ApiResponse(false, board.get().getBoardId() + "해당 게시글이 존재하지 않습니다.");
    } else {
      // boardConverter.requestToBoard(boardRequest, board.get());
      board.get().setDelYn("Y");
      return new ApiResponse(true, board.get().getBoardId() + "성공적으로 삭제했습니다.");
    }
  }
}

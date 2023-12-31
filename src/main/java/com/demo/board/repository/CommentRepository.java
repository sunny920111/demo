package com.demo.board.repository;

import com.demo.board.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryQueryDsl {

  List<Comment> findAllByBoardIdAndDelYn(long boardId, String delYn);
}

package com.example.demo.board.repository;

import java.util.ArrayList;

// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.board.domain.BoardVO;
import com.example.demo.board.domain.ReplyVO;

@Repository
public interface ReplyRepository extends PagingAndSortingRepository<ReplyVO, Integer>{

  ArrayList<ReplyVO> findAllByBoardOrderByIdDesc(BoardVO boardEntity);
}

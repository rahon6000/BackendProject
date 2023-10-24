package com.example.demo.board;

import java.util.ArrayList;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends PagingAndSortingRepository<ReplyVO, Integer>{

  ArrayList<ReplyVO> findAllByBoardOrderByIdDesc(BoardVO boardEntity);
  ArrayList<ReplyVO> findAllByBoardOrderByIdAsc(BoardVO boardEntity);
}

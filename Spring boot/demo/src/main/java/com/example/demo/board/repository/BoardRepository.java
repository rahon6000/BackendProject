package com.example.demo.board.repository;

// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.board.domain.BoardVO;

@Repository
public interface BoardRepository extends PagingAndSortingRepository<BoardVO, Integer>{
  
}

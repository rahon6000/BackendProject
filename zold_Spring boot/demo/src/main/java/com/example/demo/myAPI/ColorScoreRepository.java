package com.example.demo.myAPI;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ColorScoreRepository extends CrudRepository<ColorScore, Integer>{
  
}

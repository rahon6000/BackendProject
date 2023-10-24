package com.example.demo.myAPI;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.myAPI.ColorScore;

@Repository
public interface ColorScoreRepository extends CrudRepository<ColorScore, Integer>{
  
}

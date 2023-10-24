package com.example.demo.myAPI;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity @Data
public class ColorScore {
  
  @Id
  @GeneratedValue(generator = "ColorScore_sequence")
  private int id;

  private Float score;
  private int[] qColor = new int[3];
  private int[] aColor = new int[3];
  private int elapse;
}

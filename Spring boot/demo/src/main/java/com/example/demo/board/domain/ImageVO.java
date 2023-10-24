package com.example.demo.board.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import lombok.Data;

@Entity
@Data
public class ImageVO {
  
  @Id
  @GeneratedValue(generator = "img_sequence")
  private int id;

  @ManyToOne
  private BoardVO board;
  private String savedFileName;

}

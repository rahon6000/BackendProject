package com.example.demo.board;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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

  @ManyToOne(fetch = FetchType.LAZY)
  private BoardVO board;
  private String savedFileName;

}

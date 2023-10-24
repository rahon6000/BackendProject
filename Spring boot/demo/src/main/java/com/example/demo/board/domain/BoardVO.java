package com.example.demo.board.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

import lombok.Data;

@Entity
@Data
public class BoardVO {
  
  @Id
  @GeneratedValue(generator = "board_sequence")
  private int id;

  private String author;
  private String title;
  private String content;
  private Date writeDate;
  private boolean isFile;

}

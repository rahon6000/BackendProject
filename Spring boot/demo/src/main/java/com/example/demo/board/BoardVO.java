package com.example.demo.board;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import java.util.Date;

import lombok.Data;

@Entity
@Data
public class BoardVO {
  
  @Id
  @GeneratedValue(generator = "board_sequence")
  private int id;

  @NotBlank
  private String author;
  @NotBlank
  private String title;
  @NotBlank
  private String content;
  private Date writeDate;
  private boolean isFile;

}

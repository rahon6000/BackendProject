package com.example.demo.board.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

import lombok.Data;

@Entity
@Data
public class ReplyVO {
  
  @Id
  @GeneratedValue(generator = "reply_sequence")
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  private BoardVO board;
  private String author;
  private String content;

  @CreationTimestamp
  private Timestamp writeDate;

}

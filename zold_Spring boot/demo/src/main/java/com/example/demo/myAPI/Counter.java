package com.example.demo.myAPI;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Entity
public class Counter {

  @Id
  @ColumnDefault(value = "(current_date)")
  private Date id;

  private int today;
  private int total;
}

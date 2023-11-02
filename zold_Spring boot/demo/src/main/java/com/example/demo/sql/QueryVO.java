package com.example.demo.sql;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class QueryVO {

  @NotBlank
  String query;
}

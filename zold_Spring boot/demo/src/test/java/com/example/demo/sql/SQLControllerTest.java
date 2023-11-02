package com.example.demo.sql;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(SQLController.class)
public class SQLControllerTest {

  @Autowired
  MockMvc mvc;

  @Test @DisplayName("sql 컨트롤러")
  void testMapToSubmitSQL() throws Exception {
    // set

    // when

    // then
    mvc.perform(MockMvcRequestBuilders.post("/sql")
      .param("query", "SHOW DATABASE"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcResultHandlers.print());
  }
}

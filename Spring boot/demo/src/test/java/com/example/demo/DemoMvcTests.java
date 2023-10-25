package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.myAPI.ColorScore;
import com.example.demo.myAPI.ColorScoreRepository;
import com.example.demo.myAPI.MyAPIController;

@WebMvcTest(MyAPIController.class)
public class DemoMvcTests {

  @Autowired
  MockMvc mvc;

  @MockBean
  ColorScoreRepository colorScoreRepository;

  @Test
  @DisplayName("테스트 이름")
  @ParameterizedTest(name = "{displayName}")
  @ValueSource(strings = {""})
  public void getReturn() throws Exception {
    //
    ColorScore mockE = new ColorScore();
    mockE.setScore(100f);
    List<ColorScore> list = List.of(mockE);
    Mockito.when(colorScoreRepository.findAll()).thenReturn(list);


    //
    mvc.perform(MockMvcRequestBuilders.get("/API/color/reqav"))
      .andDo(MockMvcResultHandlers.print())
      .andExpect(MockMvcResultMatchers.status().isOk());
  }

}

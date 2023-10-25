package com.example.demo.sql;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SQLController {
 
  // Simplest get mapping to sql.html in resources.

  // not using bean this time
  SQLService sqlService = new SQLService();
  
  @GetMapping(value="/sql")
  public String mapToSQL() {
    return "sql";
  }
  
  @PostMapping(value="/sql")
  public String mapToSubmitSQL(String query, Model model) {
    ArrayList<String[]>  result = sqlService.sendQuery(query);
    model.addAttribute("result", result);
    model.addAttribute("prev_query", query);
    return "sql"; // redirect 하면 모델 정보 사라짐...
  }
  
}

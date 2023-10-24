package com.example.demo.sql;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SQLController {
 
  // Simplest get mapping to sql.html in resources.
  
  @GetMapping(value="/sql")
  public String mapToSQL(Model model) {
    model.addAttribute("sql", new QueryVO());
    return "sql";
  }
  
  @PostMapping(value="/sql")
  public void mapToSubmitSQL(@RequestParam QueryVO sql) {
    System.out.println(sql.getQuery());
  }
  
}

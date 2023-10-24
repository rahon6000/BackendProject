package com.example.demo.sql;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MyController {
 
  // Simplest get mapping to sql.html in resources.
  
  @GetMapping(value="/sql")
  public String mapToSQL() {
      return "sql";
  }
  
  // @ResponseBody
  // @PostMapping(value="/submitSQL")
  // public String mapToSubmitSQL(@RequestBody String query) {
  //   query = URLDecoder.decode(query, Charset.forName("UTF-8"));
    
  //   System.out.println(query);
  //   return "sql";
  // }
  
  // @ResponseBody
  @PostMapping(value="/sql")
  public void mapToSubmitSQL(@ModelAttribute String query) {
    System.out.println(query);
  }
  
}

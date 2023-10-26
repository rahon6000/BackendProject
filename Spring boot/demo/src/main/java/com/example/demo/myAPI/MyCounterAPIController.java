package com.example.demo.myAPI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/counter")
public class MyCounterAPIController {

  @Autowired
  private CounterService counterService;

  // Counter API!
  @ResponseBody
  @GetMapping(value="/increase")
  @CrossOrigin(originPatterns = {"https://blog.rahon.dev", "https://rahon6000.github.io"})
  public Counter increase(HttpServletRequest request) {
    // 근데 이거 서블릿 기술인데, boot 에서 따로 마련한 건 없나?
    return counterService.hit(request);
  }
  
}

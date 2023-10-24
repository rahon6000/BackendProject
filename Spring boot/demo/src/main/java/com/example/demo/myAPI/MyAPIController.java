package com.example.demo.myAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/API")
public class MyAPIController {

  @Autowired
  private ColorScoreRepository colorScoreRepository;
  
  // Color Score API!
  // @ResponseBody
  // @CrossOrigin(originPatterns = {"https://blog.rahon.dev", "https://rahon6000.github.io"})
  // @GetMapping(value="/send")
  // public void saveColorScore(
  //     @RequestParam float score,
  //     @RequestParam ArrayList<Float> Q,
  //     @RequestParam ArrayList<Float> A,
  //     @RequestParam int elapse) {
  //   ColorScore cScore = new ColorScore();
  //   cScore.setAColor(A);
  //   cScore.setElapse(elapse);
  //   cScore.setQColor(Q);
  //   cScore.setScore(score);
  //   colorScoreRepository.save(cScore);
  // }
  @ResponseBody
  @CrossOrigin //(originPatterns = {"https://blog.rahon.dev", "https://rahon6000.github.io"})
  @PostMapping(value="/color/send")
  public void saveColorScore(@RequestBody ColorScore score) {
    System.out.println(score);
    colorScoreRepository.save(score);
  }
  
  @ResponseBody
  @GetMapping("/color/reqlist")
  public List<ColorScore> requestScores() {
    List<ColorScore> result = (List<ColorScore>) colorScoreRepository.findAll();
    return result;
  }
  
  @ResponseBody
  @GetMapping("/color/reqav")
  public float requestAverage() {
    List<ColorScore> list = (List<ColorScore>) colorScoreRepository.findAll();
    if(list.size() == 0) return 0;
    float sum = 0;
    for( ColorScore c : list) {
      sum += c.getScore();
    }
    sum /= list.size();
    
    return sum;
  }

  @ResponseBody
  @GetMapping(value="/counter/increase")
  
  public String getMethodName(@RequestParam String param, @PathVariable String path) {
    System.out.println(param);
    System.out.println(path);
      return "test!";
  }
  
}

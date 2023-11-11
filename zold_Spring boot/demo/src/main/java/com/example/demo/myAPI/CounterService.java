package com.example.demo.myAPI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CounterService {

  private int timeout = 24*3600; // 초 단위
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

  @Autowired
  CounterRepository counterRepository;

  public Date getToday() throws ParseException{
    return sdf.parse(sdf.format(new Date() )); // better way?
  }

  public Counter hit(HttpServletRequest request) throws ParseException {
    Date today = getToday();

    // Counter counter = new Counter();
    // Optional<Counter> optional = counterRepository.findById(today);
    // if (optional.isPresent()) {
    //   counter = optional.get();
    // } else {
    //   if (counterRepository.count() == 0) {
    //     counter.setTotal(0);
    //   } else {
    //     Counter yesterday = counterRepository.findLast();
    //     counter.setTotal(yesterday.getTotal());
    //   }
    //   counter.setToday(0);
    //   counter.setId(today);
    //   counterRepository.save(counter);
    // }
    
    // ############# Exploit Optional wrapper class
    Counter counter = counterRepository.findById(today).orElseGet( ()->{
      Counter c = new Counter();
      c.setTotal(counterRepository.findLast().orElseGet(()-> new Counter() ).getTotal());
      c.setToday(0);
      try {
        c.setId(getToday());
      } catch (ParseException e) {
        e.printStackTrace();
      }
      return c;
    });

    HttpSession session = request.getSession();
    session.setMaxInactiveInterval(timeout);
    if (session.isNew()) {
      log.info("new session visited");
      counter.setToday(counter.getToday() + 1);
      counter.setTotal(counter.getTotal() + 1);
      counterRepository.save(counter);
    } else {
      long elapse = new Date().getTime() - session.getCreationTime();
      if (elapse > timeout * 1000) { // 세션을 갱신해도, 만든지 하루 지나면 죽인다.
        session.invalidate();
        request.getSession(true);
        counter.setToday(counter.getToday() + 1);
        counter.setTotal(counter.getTotal() + 1);
        counterRepository.save(counter);
        log.info("renew old session.");
      } else {
        log.info("old session revisited");
      }
    }
    return counter;
  }

}

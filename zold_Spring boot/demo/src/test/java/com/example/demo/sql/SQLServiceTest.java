package com.example.demo.sql;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest // Load everything
@Log4j2
public class SQLServiceTest {

  @Autowired
  SQLService sqlService;

  @Test
  void testSendQuery() {

    ArrayList<String[]> result = sqlService.sendQuery("SHOW DATABASES");
    // Object result = sqlService.connectToDB("localhost", "root", "1234");
    log.info(result);
  }
}

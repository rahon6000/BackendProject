package com.example.demo.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service @Log4j2
public class SQLService {

  private Connection con;

  @Value("${spring.datasource.url}")
  private String URL;
  @Value("${spring.datasource.username}")
  private String USER;
  @Value("${spring.datasource.password}")
  private String PWD;

  public SQLService() {
    log.info(URL); // It is null... @Value cannot be used at the time of construction?
    // try {
    //   Class.forName("com.mysql.cj.jdbc.Driver");
    //   con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_server", 
    //   "root", 
    //   "1234");
    //   log.info("DB connected.");
    // } catch (Exception e) {
    //   log.info("connection failed.");
    // }
  }

  public ArrayList<String[]> sendQuery(String query) {
    // The old way
    log.info(URL); // It is OK now

    con = connectToDB(URL, USER, PWD);

    ResultSet rs = null;
    ArrayList<String[]> result = new ArrayList<>();
    try {
      Statement stat = con.createStatement();
      rs = stat.executeQuery(query);
      int size = rs.getMetaData().getColumnCount();
      while(rs.next()){
        String[] buffer = new String[size+1];
        for(int i = 1; i <= size; i++) {
          buffer[i] = rs.getString(i);
        }
        result.add(buffer);
      }
    } catch (Exception e) {
      result.add(new String[]{"SQL Error : " + e});
    }
    log.info(result);
    return result;
    
  }

  Connection connectToDB(String url, String user, String pwd) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con = DriverManager.getConnection(url, 
      user, 
      pwd);
      log.info("DB connected.");
    } catch (Exception e) {
      log.info("connection failed.");
    }
    return con;
  }
}

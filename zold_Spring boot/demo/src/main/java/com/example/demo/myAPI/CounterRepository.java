package com.example.demo.myAPI;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
public interface CounterRepository extends CrudRepository<Counter,Date>{
  @Query(value = "SELECT * FROM counter c ORDER BY c.id DESC LIMIT 1;", nativeQuery = true)
  Counter findLast();
}

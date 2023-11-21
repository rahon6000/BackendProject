package rahon.example.demo;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface DemoRepository extends ReactiveCrudRepository<Actor, Long>{
    
    // @Query("SELECT * FROM Actor ac WHERE ac.first_name = :firstname")
    Flux<Actor> findAllByFirstName(
        // @Param("firstname") 
        String firstname);

    
    
}

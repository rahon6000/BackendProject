package rahon.example.demo;

import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class DemoController {

    private int count = 0;
    private DemoRepository demoRepository;

    public DemoController(DemoRepository demoRepository){
        this.demoRepository = demoRepository;
    }

    @GetMapping("/test")
    public Mono<String> mapTest() {
        return Mono.just("Hello world!"); // ~ conventional Controller
    }

    @GetMapping("/infstream")
    public Flux<Integer> mapInfStream() {
        Stream<Integer> stream = Stream.iterate(count, i -> i + 1);
        return Flux.fromStream(stream); // send real-time.
    }

    @GetMapping("/memory")
    public Mono<Integer> mapStream() {
        count ++;
        return Mono.just(count);
    }

    @GetMapping("/actor/{id}")
    public Mono<Actor> mapActorId( @PathVariable("id") long id){
        return demoRepository.findById(id);
    }

    @GetMapping("/actor/all")
    public Flux<Actor> mapActorAll(){
        return demoRepository.findAll();
    }

    @GetMapping("/actor/firstname/{firstname}")
    public Flux<Actor> mapActorFirstname(@PathVariable("firstname") String firstname){
        return demoRepository.findAllByFirstName(firstname.toUpperCase());
    }
}

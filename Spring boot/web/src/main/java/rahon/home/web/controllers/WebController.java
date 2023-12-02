package rahon.home.web.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class WebController {
    
    @GetMapping(value="/thread")
    public String getThread() {
        return Thread.currentThread().toString();
    }
    
}

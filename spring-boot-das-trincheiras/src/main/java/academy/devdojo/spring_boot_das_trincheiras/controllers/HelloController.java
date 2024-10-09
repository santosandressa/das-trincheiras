package academy.devdojo.spring_boot_das_trincheiras.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/api/v1/greetings")
@RequiredArgsConstructor
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        log.info("Executing hello method");
        return "OMAE WA MOU SHINDE IRU";
    }

    @RequestMapping(value="/bye", method = RequestMethod.GET)
    public String bye() {
        log.info("Executing bye method");
        return "NANI ?";
    }

    @PostMapping
    public Long create(@RequestBody String name){
        log.info("Creating a new greeting for {}", name);
        return ThreadLocalRandom.current().nextLong(1, 1000);
    }
}

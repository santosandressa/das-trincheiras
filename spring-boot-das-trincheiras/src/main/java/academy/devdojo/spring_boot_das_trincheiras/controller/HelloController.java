package academy.devdojo.spring_boot_das_trincheiras.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/greetings")
@RequiredArgsConstructor
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String hello() {
        logger.info("Executing hello method");
        return "OMAE WA MOU SHINDE IRU";
    }

    @RequestMapping(value="/bye", method = RequestMethod.GET)
    public String bye() {
        logger.info("Executing bye method");
        return "NANI ?";
    }
}

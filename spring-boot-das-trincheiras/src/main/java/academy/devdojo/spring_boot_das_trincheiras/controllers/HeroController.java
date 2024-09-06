package academy.devdojo.spring_boot_das_trincheiras.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/heroes")
public class HeroController {

    private static final List<String> HEROES = List.of("Guts", "Zoro", "Kakashi", "Goku");

    @GetMapping
    public List<String> listAllHeroes() {
        return HEROES;
    }

    @GetMapping("/filter")
    public List<String> listAllByParam(@RequestParam(required = false) String name) {
        if (name == null) {
            return HEROES;
        } else {
            return HEROES.stream().filter(hero -> hero.equalsIgnoreCase(name)).toList();
        }
    }

    @GetMapping("filterList")
    public List<String> listAllHeroesParamList(@RequestParam List<String> names){
        return HEROES.stream().filter(names::contains).toList();
    }

    @GetMapping("/{name}")
    public String findByName(@PathVariable String name) {
        return HEROES
                .stream()
                .filter(hero -> hero.equalsIgnoreCase(name))
                .findFirst().orElse("Hero not found");
    }

}

package academy.devdojo.spring_boot_das_trincheiras.controllers;

import academy.devdojo.spring_boot_das_trincheiras.domain.Anime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {

    @GetMapping
    public List<Anime> listAll() {
        log.info(Thread.currentThread().getName());
        return Anime.createAnimeList();
    }

    @GetMapping("/filter")
    public List<Anime> filter(@RequestParam(required = false) String name) {
        var animes = Anime.createAnimeList();
        if (name == null) return animes;
        return animes
                .stream()
                .filter(anime1 -> anime1.getName().equalsIgnoreCase(name))
                .toList();
    }

    @GetMapping("/{id}")
    public Anime findById(@PathVariable Long id) {
        return Anime.createAnimeList()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}

package academy.devdojo.spring_boot_das_trincheiras.controllers;

import academy.devdojo.spring_boot_das_trincheiras.domain.Anime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/v1/animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {

    private final Random random = new Random();

    @GetMapping
    public List<Anime> listAll() {
        log.info(Thread.currentThread().getName());
        return Anime.getAnimeList();
    }

    @GetMapping("/filter")
    public List<Anime> filter(@RequestParam(required = false) String name) {
        var animes = Anime.getAnimeList();
        if (name == null) return animes;
        return animes
                .stream()
                .filter(anime1 -> anime1.getName().equalsIgnoreCase(name))
                .toList();
    }

    @GetMapping("/{id}")
    public Anime findById(@PathVariable Long id) {
        return Anime.getAnimeList()
                .stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody Anime anime) {
        anime.setId(random.nextLong());
        Anime.getAnimeList().add(anime);
        return ResponseEntity.ok(anime);
    }
}

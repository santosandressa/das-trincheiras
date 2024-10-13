package academy.devdojo.spring_boot_das_trincheiras.controllers;

import academy.devdojo.spring_boot_das_trincheiras.domain.Anime;
import academy.devdojo.spring_boot_das_trincheiras.dto.request.AnimeDTORequest;
import academy.devdojo.spring_boot_das_trincheiras.dto.response.AnimeDTOResponse;
import academy.devdojo.spring_boot_das_trincheiras.mapper.AnimeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animes")
@Slf4j
@RequiredArgsConstructor
public class AnimeController {

    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;

    @GetMapping
    public ResponseEntity<List<AnimeDTOResponse>> listAll() {
        var animes = Anime.getAnimeList();
        List<AnimeDTOResponse> animeDTOResponses = MAPPER.toAnimeDTOResponseList(animes);
        log.info(Thread.currentThread().getName());
        return ResponseEntity.ok(animeDTOResponses);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<AnimeDTOResponse>> filter(@RequestParam(required = false) String name) {
        var animes = Anime.getAnimeList();
        List<AnimeDTOResponse> animeDTOResponses = MAPPER.toAnimeDTOResponseList(animes);

        if (name == null) {
            return ResponseEntity.ok(animeDTOResponses);
        }

        return ResponseEntity.ok(animeDTOResponses
                .stream()
                .filter(anime1 -> anime1.getName().equalsIgnoreCase(name))
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeDTOResponse> findById(@PathVariable Long id) {
        Anime anime = Anime.getAnimeList()
                .stream()
                .filter(animeFilter -> animeFilter.getId().equals(id))
                .findFirst()
                .orElse(null);

        AnimeDTOResponse animeDTOResponse = MAPPER.toAnimeDTOResponse(anime);
        return ResponseEntity.ok(animeDTOResponse);
    }

    @PostMapping
    public ResponseEntity<AnimeDTOResponse> save(@RequestBody AnimeDTORequest animeDTORequest) {
        Anime anime = MAPPER.toAnime(animeDTORequest);
        AnimeDTOResponse animeDTOResponse = MAPPER.toAnimeDTOResponse(anime);
        Anime.getAnimeList().add(anime);
        return ResponseEntity.status(HttpStatus.CREATED).body(animeDTOResponse);
    }
}

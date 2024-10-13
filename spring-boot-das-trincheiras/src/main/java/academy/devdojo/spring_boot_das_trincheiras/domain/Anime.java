package academy.devdojo.spring_boot_das_trincheiras.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Anime {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private static List<Anime> animes = new ArrayList<>();

    static {
        var ninjaKamui = Anime.builder().id(1L).name("Ninja Kamui").createdAt(LocalDateTime.now()).build();
        var kaiju = Anime.builder().id(2L).name("Kaijuu-gou").createdAt(LocalDateTime.now()).build();
        var kimetsuNoYaiba = Anime.builder().id(3L).name("Kimetsu no Yaiba").createdAt(LocalDateTime.now()).build();

        animes.addAll(List.of(ninjaKamui, kaiju, kimetsuNoYaiba));
    }

    public static List<Anime> getAnimeList() {
        return animes;
    }
}

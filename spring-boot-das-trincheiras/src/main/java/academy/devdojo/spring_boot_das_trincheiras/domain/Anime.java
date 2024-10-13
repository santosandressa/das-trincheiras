package academy.devdojo.spring_boot_das_trincheiras.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Anime {

    private Long id;
    private String name;
    private static List<Anime> animes = new ArrayList<>();

    static {
        var ninjaKamui = new Anime(1L, "Ninja Kamui");
        var kaiju = new Anime(2L, "Kaijuu-gou");
        var kimetsuNoYaiba = new Anime(3L, "Kimetsu no Yaiba");

        animes.addAll(List.of(ninjaKamui, kaiju, kimetsuNoYaiba));
    }

    public static List<Anime> getAnimeList() {
        return animes;
    }
}

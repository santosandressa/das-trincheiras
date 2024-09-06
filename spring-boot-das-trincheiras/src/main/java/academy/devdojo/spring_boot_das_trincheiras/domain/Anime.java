package academy.devdojo.spring_boot_das_trincheiras.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Anime {
    private Long id;
    private String name;

    public static List<Anime> createAnimeList() {
        var ninjaKamui = new Anime(1L, "Ninja Kamui");
        var kaiju = new Anime(2L, "Kaijuu-gou");
        var kimetsuNoYaiba = new Anime(3L, "Kimetsu no Yaiba");
        return List.of(ninjaKamui, kaiju, kimetsuNoYaiba);
    }
}

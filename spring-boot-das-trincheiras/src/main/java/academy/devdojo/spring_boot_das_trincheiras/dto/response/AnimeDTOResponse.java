package academy.devdojo.spring_boot_das_trincheiras.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnimeDTOResponse {
    private Long id;
    private String name;
}

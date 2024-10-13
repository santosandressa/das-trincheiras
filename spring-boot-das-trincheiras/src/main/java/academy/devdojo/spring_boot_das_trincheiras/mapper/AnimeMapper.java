package academy.devdojo.spring_boot_das_trincheiras.mapper;

import academy.devdojo.spring_boot_das_trincheiras.domain.Anime;
import academy.devdojo.spring_boot_das_trincheiras.dto.request.AnimeDTORequest;
import academy.devdojo.spring_boot_das_trincheiras.dto.response.AnimeDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnimeMapper {

    AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    List<AnimeDTOResponse> toAnimeDTOResponseList(List<Anime> animes);

    AnimeDTOResponse toAnimeDTOResponse(Anime anime);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target= "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(1, 1000))")
    Anime toAnime(AnimeDTORequest animeDTOResponse);

}

package academy.devdojo.spring_boot_das_trincheiras.mapper;

import academy.devdojo.spring_boot_das_trincheiras.domain.Producer;
import academy.devdojo.spring_boot_das_trincheiras.dto.request.ProducerDTORequest;
import academy.devdojo.spring_boot_das_trincheiras.dto.response.ProducerDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProducerMapper {
    ProducerMapper INSTANCE = Mappers.getMapper(ProducerMapper.class);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target= "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(1, 1000))")
    Producer toProducer(ProducerDTORequest producerDTORequest);

    ProducerDTOResponse toProducerGetResponse(Producer producer);

    List<ProducerDTOResponse> toProducerGetResponseList(List<Producer> producers);
}

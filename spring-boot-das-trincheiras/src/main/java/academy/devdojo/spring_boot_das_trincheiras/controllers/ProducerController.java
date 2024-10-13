package academy.devdojo.spring_boot_das_trincheiras.controllers;

import academy.devdojo.spring_boot_das_trincheiras.domain.Producer;
import academy.devdojo.spring_boot_das_trincheiras.dto.request.ProducerDTORequest;
import academy.devdojo.spring_boot_das_trincheiras.dto.response.ProducerDTOResponse;
import academy.devdojo.spring_boot_das_trincheiras.mapper.ProducerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/producers")
@Slf4j
@RequiredArgsConstructor
public class ProducerController {

    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;

    @GetMapping
    public List<Producer> listAll() {
        log.info(Thread.currentThread().getName());
        return Producer.geProducerList();
    }

    @GetMapping("/filter")
    public List<Producer> filter(@RequestParam(required = false) String name) {
        var producer = Producer.geProducerList();
        if (name == null) return producer;
        return producer
                .stream()
                .filter(anime1 -> anime1.getName().equalsIgnoreCase(name))
                .toList();
    }

    @GetMapping("/{id}")
    public Producer findById(@PathVariable Long id) {
        return Producer.geProducerList()
                .stream()
                .filter(producer -> producer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            headers = "x-api-key=v1")
    public ResponseEntity<ProducerDTOResponse> save(@RequestBody ProducerDTORequest producerDTORequest) {
        Producer producer = MAPPER.toProducer(producerDTORequest);
        ProducerDTOResponse response = MAPPER.toProducerDTOResponse(producer);

        Producer.geProducerList().add(
                producer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

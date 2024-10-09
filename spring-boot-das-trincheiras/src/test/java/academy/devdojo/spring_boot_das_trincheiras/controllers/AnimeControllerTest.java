package academy.devdojo.spring_boot_das_trincheiras.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnimeControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private RestTemplate restTemplate;

    private String baseURL;

    @BeforeEach
    void setUp() {
        restTemplate = restTemplateBuilder.build();
        baseURL = "http://localhost:" + port + "/spring-boot-das-trincheiras/v1";
    }

    @Test
    @DisplayName("Should return a list of animes")
    void given_animesURI_shouldReturnListOfAnimes() {
        String url = baseURL + "/animes";
        String response = restTemplate.getForObject(url, String.class);
        assertNotNull(response);
    }

    @Test
    @DisplayName("Should return a list of animes filtered by name")
    void given_animesFilterURI_shouldReturnListOfAnimesFilteredByName() {
        String url = baseURL + "/animes/filter?name=Dragon Ball";
        String response = restTemplate.getForObject(url, String.class);
        assertNotNull(response);
    }

    @Test
    @DisplayName("Should return an anime by id")
    void given_animesIdURI_shouldReturnAnAnimeById() {
        String url = baseURL + "/animes/1";
        String response = restTemplate.getForObject(url, String.class);
        assertNotNull(response);
    }

}
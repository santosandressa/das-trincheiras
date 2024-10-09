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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HeroControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private RestTemplate restTemplate;

    private String baseURL;

    @BeforeEach
    void setUp() {
        restTemplate = this.restTemplateBuilder.build();
        baseURL = "http://localhost:" + port + "/spring-boot-das-trincheiras/v1/heroes";
    }

    @Test
    @DisplayName("Should return a list of heroes")
    void given_heroesURI_shouldReturnListOfHeroes() {
        String url = baseURL;
        String[] response = restTemplate.getForObject(url, String[].class);
        assertNotNull(response);
        assertEquals(4, response.length);
    }

    @Test
    @DisplayName("Should return a list of heroes filtered by name")
    void given_heroesFilterURI_shouldReturnListOfHeroesFilteredByName() {
        String url = baseURL + "/filter?name=Guts";
        String[] response = restTemplate.getForObject(url, String[].class);
        assertNotNull(response);
        assertEquals(1, response.length);
    }

    @Test
    @DisplayName("Shoul return all heroes when name is null or empty")
    void given_heroesFilterURI_shouldReturnAllHeroesWhenNameIsNull() {
        String url = baseURL + "/filter";
        String[] response = restTemplate.getForObject(url, String[].class);
        assertNotNull(response);
        assertEquals(4, response.length);
    }

    @Test
    @DisplayName("Should return a list of heroes filtered by list of names")
    void given_heroesFilterListURI_shouldReturnListOfHeroesFilteredByList() {
        String url = baseURL + "/filterList?names=Guts&names=Zoro";
        String[] response = restTemplate.getForObject(url, String[].class);
        assertNotNull(response);
        assertEquals(2, response.length);
    }

    @Test
    @DisplayName("Should return a hero by name")
    void given_heroesNameURI_shouldReturnHeroByName() {
        String url = baseURL + "/Guts";
        String response = restTemplate.getForObject(url, String.class);
        assertEquals("Guts", response);
    }
}
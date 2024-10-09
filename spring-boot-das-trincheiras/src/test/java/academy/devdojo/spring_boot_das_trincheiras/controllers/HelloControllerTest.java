package academy.devdojo.spring_boot_das_trincheiras.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private RestTemplate restTemplate;

    private String baseURL;

    @BeforeEach
    void setUp() {
        restTemplate = restTemplateBuilder.build();
        baseURL = "http://localhost:" + port + "/spring-boot-das-trincheiras/api/v1";
    }

    @Test
    @DisplayName("Should return a bye message")
    void given_byeURI_shouldReturnByeMessage() {
        String url = baseURL + "/greetings/bye";
        String response = restTemplate.getForObject(url, String.class);
        assertEquals("NANI ?", response);
    }

    @Test
    @DisplayName("Should return a hello message")
    void given_helloURI_shouldReturnHelloMessage() {
        String url = baseURL + "/greetings/hello";
        String response = restTemplate.getForObject(url, String.class);
        assertEquals("OMAE WA MOU SHINDE IRU", response);
    }

    @Test
    @DisplayName("Should return a greeting id")
    void given_name_shouldReturnGreetingId() {
        String url = baseURL + "/greetings";
        Long response = restTemplate.postForObject(url, "John Doe", Long.class);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Should return 400 for create endpoint with empty body")
    void given_emptyName_shouldReturnBadRequest() {
        String url = baseURL + "/greetings";
        try {
            restTemplate.postForObject(url, "", Long.class);
        } catch (HttpClientErrorException e) {
            assertThat(e.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    @DisplayName("Should return 400 for create endpoint with null body")
    void given_nullName_shouldReturnBadRequest() {
        String url = baseURL + "/greetings";
        try {
            restTemplate.postForObject(url, null, Long.class);
        } catch (HttpClientErrorException e) {
            assertThat(e.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }
    }
}
package com.codecool.classicalregistry.controller;

import com.codecool.classicalregistry.model.DTO.ComposerDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ComposerIntegrationTest {

    @LocalServerPort
    private Integer port;

    private final String BASE_URL = "http://localhost:";

    private static TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testGetEndpointForListComposers() {
        ResponseEntity<ComposerDTO[]> responseEntity = restTemplate.getForEntity(BASE_URL+port+"/composer", ComposerDTO[].class);
        List<ComposerDTO> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(6, actual.size());
        Assertions.assertEquals("Johann Sebastian Bach", actual.get(0).getName());
    }

    @Test
    public void testGetEndpointForFindComposerById() {
        ComposerDTO composer = restTemplate.getForObject(BASE_URL+port+"/composer/1", ComposerDTO.class);
        Assertions.assertEquals(1685, composer.getYearOfBirth());
        Assertions.assertEquals("Johann Sebastian Bach", composer.getName());
    }

    @Test
    public void testPostEndPointAddOneComposer() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ComposerDTO> httpEntity = new HttpEntity<>(
                new ComposerDTO(0, "teszt", "teszt", 2000, 1L), headers);

        ResponseEntity<Long> postResponse = restTemplate.postForEntity(BASE_URL+port+"/composer", httpEntity, Long.class);
        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        ComposerDTO composerDTO = restTemplate.getForObject(BASE_URL+port+"/composer/7", ComposerDTO.class);
        Assertions.assertEquals("teszt", composerDTO.getName());

    }
}

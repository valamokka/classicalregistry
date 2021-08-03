package com.codecool.classicalregistry.controller;

import com.codecool.classicalregistry.model.DTO.ComposerDTO;
import com.codecool.classicalregistry.model.DTO.CompositionDTO;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
        ResponseEntity<ComposerDTO[]> responseEntity = restTemplate.getForEntity(BASE_URL + port + "/composer", ComposerDTO[].class);
        List<ComposerDTO> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(6, actual.size());
        Assertions.assertEquals("Johann Sebastian Bach", actual.get(0).getName());
    }

    @Test
    public void testGetEndpointForFindComposerById() {
        ComposerDTO composer = restTemplate.getForObject(BASE_URL + port + "/composer/1", ComposerDTO.class);
        Assertions.assertEquals(1685, composer.getYearOfBirth());
        Assertions.assertEquals("Johann Sebastian Bach", composer.getName());
    }

    @Test
    public void testPostEndPointAddOneComposer() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ComposerDTO> httpEntity = new HttpEntity<>(
                new ComposerDTO(0, "Antonio Vivaldi", "italian", 1678, 2), headers);

        ResponseEntity<Long> postResponse = restTemplate.postForEntity(BASE_URL + port + "/composer", httpEntity, Long.class);
        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        ComposerDTO composerDTO = restTemplate.getForObject(BASE_URL + port + "/composer/7", ComposerDTO.class);
        Assertions.assertEquals("Antonio Vivaldi", composerDTO.getName());

    }

    @Test
    public void testPutEndPoint_updateOneComposerById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ComposerDTO composerDTO = restTemplate.getForObject(BASE_URL + port + "/composer/1", ComposerDTO.class);

        composerDTO.setName("Changed Johann Sebastian Bach");

        HttpEntity<ComposerDTO> httpEntity = new HttpEntity<>(composerDTO, headers);
        restTemplate.put(BASE_URL + port + "/composer/" + composerDTO.getId(), httpEntity);

        ComposerDTO composerDTOReceived = restTemplate.getForObject(BASE_URL + port + "/composer/1", ComposerDTO.class);

        Assertions.assertEquals("Changed Johann Sebastian Bach", composerDTOReceived.getName());

    }

    @Test
    public void testDeleteEndPoint_deletingOneComposerDTO() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<ComposerDTO[]> responseEntityBeforeDeleting = restTemplate.getForEntity(BASE_URL + port + "/composer", ComposerDTO[].class);
        List<ComposerDTO> beforeDeleting = Arrays.asList(responseEntityBeforeDeleting.getBody());
        Assertions.assertEquals(7, beforeDeleting.size());
        Assertions.assertEquals("Antonio Vivaldi", beforeDeleting.get(6).getName());

        restTemplate.delete(BASE_URL + port + "/composer/7");
        ResponseEntity<ComposerDTO[]> responseEntityAfterDeleting = restTemplate.getForEntity(BASE_URL + port + "/composer", ComposerDTO[].class);
        List<ComposerDTO> afterDeleting = Arrays.asList(responseEntityAfterDeleting.getBody());
        Assertions.assertEquals(6, afterDeleting.size());
    }

    @Test
    public void testAllCompositionsForProvidedComposer() {
        ResponseEntity<CompositionDTO[]> responseEntity = restTemplate.getForEntity(BASE_URL + port + "/composer/1/composition", CompositionDTO[].class);
        List<CompositionDTO> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals("Brandenburg Concerto No.3", actual.get(0).getTitle());


    }
}

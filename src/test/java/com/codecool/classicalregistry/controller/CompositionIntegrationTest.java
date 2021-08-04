package com.codecool.classicalregistry.controller;

import com.codecool.classicalregistry.model.Composition;
import com.codecool.classicalregistry.model.DTO.CompositionDTO;
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
public class CompositionIntegrationTest {

    @LocalServerPort
    private Integer port;

    private final String BASE_URL = "http://localhost:";

    private static TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testGetEndpoint_listCompositions() {
        ResponseEntity<CompositionDTO[]> responseEntity = restTemplate.getForEntity(BASE_URL+port+"/composition", CompositionDTO[].class);
        List<CompositionDTO> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(6, actual.size());
        Assertions.assertEquals("Eine kleine Nachtmusik", actual.get(0).getTitle());
    }

    @Test
    public void testGetEndpoint_findCompositionById() {
        Composition composition = restTemplate.getForObject(BASE_URL+port+"/composition/1", Composition.class);
        Assertions.assertEquals("Eine kleine Nachtmusik", composition.getTitle());
    }

    @Test
    public void testPostEndPoint_AddOneComposition() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CompositionDTO> httpEntity = new HttpEntity<>(
                new CompositionDTO(0, "Symphony No. 7", 6), headers);

        ResponseEntity<String> postResponse = restTemplate.postForEntity(BASE_URL + port + "/composition", httpEntity, String.class);
        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        CompositionDTO compositionDTO = restTemplate.getForObject(BASE_URL + port + "/composition/7", CompositionDTO.class);
        Assertions.assertEquals("Symphony No. 7", compositionDTO.getTitle());

    }

    @Test
    public void testPutEndPoint_updateOneCompositionById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        CompositionDTO compositionDTO = restTemplate.getForObject(BASE_URL + port + "/composition/1", CompositionDTO.class);
        compositionDTO.setTitle("Changed Eine kleine Nachtmusik");

        HttpEntity<CompositionDTO> httpEntity = new HttpEntity<>(compositionDTO, headers);
        restTemplate.put(BASE_URL + port + "/composition/" + compositionDTO.getId(), httpEntity);

        CompositionDTO compositionDTOReceived = restTemplate.getForObject(BASE_URL + port + "/composition/1", CompositionDTO.class);

        Assertions.assertEquals("Changed Eine kleine Nachtmusik", compositionDTOReceived.getTitle());

    }

    @Test
    public void testDeleteEndPoint_deletingOneCompositionDTO() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CompositionDTO> httpEntity = new HttpEntity<>(
                new CompositionDTO(0, "Symphony No. 7", 6), headers);

        ResponseEntity<String> postResponse = restTemplate.postForEntity(BASE_URL + port + "/composition", httpEntity, String.class);
        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        ResponseEntity<CompositionDTO[]> responseEntityBeforeDeleting = restTemplate.getForEntity(BASE_URL + port + "/composition", CompositionDTO[].class);
        List<CompositionDTO> beforeDeleting = Arrays.asList(responseEntityBeforeDeleting.getBody());
        Assertions.assertEquals(7, beforeDeleting.size());
        Assertions.assertEquals("Symphony No. 7", beforeDeleting.get(6).getTitle());

        restTemplate.delete(BASE_URL + port + "/composition/7");
        ResponseEntity<CompositionDTO[]> responseEntityAfterDeleting = restTemplate.getForEntity(BASE_URL + port + "/composition", CompositionDTO[].class);
        List<CompositionDTO> afterDeleting = Arrays.asList(responseEntityAfterDeleting.getBody());
        Assertions.assertEquals(6, afterDeleting.size());
    }

}

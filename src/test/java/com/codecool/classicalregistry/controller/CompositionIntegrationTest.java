package com.codecool.classicalregistry.controller;

import com.codecool.classicalregistry.model.Composition;
import com.codecool.classicalregistry.model.DTO.CompositionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
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
    public void testGetEndpointForListCompositions() {
        ResponseEntity<CompositionDTO[]> responseEntity = restTemplate.getForEntity(BASE_URL+port+"/composition", CompositionDTO[].class);
        List<CompositionDTO> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(6, actual.size());
        Assertions.assertEquals("Eine kleine Nachtmusik", actual.get(0).getTitle());
    }

    @Test
    public void testGetEndpointForFindCompositionById() {
        Composition composition = restTemplate.getForObject(BASE_URL+port+"/composition/1", Composition.class);
        Assertions.assertEquals("Eine kleine Nachtmusik", composition.getTitle());
    }

}

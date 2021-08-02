package com.codecool.classicalregistry.controller;


import com.codecool.classicalregistry.model.ClassicalPeriod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ClassicalPeriodIntegrationTest {

    @LocalServerPort
    private Integer port;

    private final String BASE_URL = "http://localhost:";

    private static TestRestTemplate restTemplate = new TestRestTemplate();


    @Test
    public void testGetEndpointForListClassicalPeriods() {
        ResponseEntity<ClassicalPeriod[]> responseEntity = restTemplate.getForEntity(BASE_URL+port+"/period", ClassicalPeriod[].class);
        List<ClassicalPeriod> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(4, actual.size());
        Assertions.assertEquals("Baroque", actual.get(1).getName());
    }

    @Test
    public void testGetEndpointForFindClassicalPeriodById() {
        ClassicalPeriod classicalPeriod = restTemplate.getForObject(BASE_URL+port+"/period/1", ClassicalPeriod.class);
        Assertions.assertEquals(1400, classicalPeriod.getYearOfBeginning());
        Assertions.assertEquals("Renaissance", classicalPeriod.getName());
    }

    @Test
    public void testPostEndPointAddOneClassicalPeriod() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ClassicalPeriod> httpEntity = new HttpEntity<>(
                new ClassicalPeriod(0, "teszt", 1900, 2000, new ArrayList<>()), headers);

        ResponseEntity<Long> postResponse = restTemplate.postForEntity(BASE_URL+port+"/period", httpEntity, Long.class);
        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        ClassicalPeriod classicalPeriod = restTemplate.getForObject(BASE_URL+port+"/period/5", ClassicalPeriod.class);
        Assertions.assertEquals("teszt", classicalPeriod.getName());

    }

}

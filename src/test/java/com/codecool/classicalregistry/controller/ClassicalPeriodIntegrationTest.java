package com.codecool.classicalregistry.controller;


import com.codecool.classicalregistry.model.ClassicalPeriod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.RestClientException;

import java.util.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ClassicalPeriodIntegrationTest {

    @LocalServerPort
    private Integer port;

    private final String BASE_URL = "http://localhost:";

    private static TestRestTemplate restTemplate = new TestRestTemplate();


    @Test
    public void testGetEndpoint_listClassicalPeriods() {
        ResponseEntity<ClassicalPeriod[]> responseEntity = restTemplate.getForEntity(BASE_URL+port+"/period", ClassicalPeriod[].class);
        List<ClassicalPeriod> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(4, actual.size());
        Assertions.assertEquals("Baroque", actual.get(1).getName());
    }

    @Test
    public void testGetEndpoint_findClassicalPeriodById() {
        ClassicalPeriod classicalPeriod = restTemplate.getForObject(BASE_URL+port+"/period/1", ClassicalPeriod.class);
        Assertions.assertEquals(1400, classicalPeriod.getYearOfBeginning());
        Assertions.assertEquals("Renaissance", classicalPeriod.getName());
    }

    @Test
    public void testPostEndPoint_addOneClassicalPeriod() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ClassicalPeriod> httpEntity = new HttpEntity<>(
                new ClassicalPeriod(0, "Modern", 1900, 2000, new ArrayList<>()), headers);

        ResponseEntity<String> postResponse = restTemplate.postForEntity(BASE_URL+port+"/period", httpEntity, String.class);
        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        ClassicalPeriod classicalPeriod = restTemplate.getForObject(BASE_URL+port+"/period/5", ClassicalPeriod.class);
        Assertions.assertEquals("Modern", classicalPeriod.getName());

    }

    @Test
    public void testPutEndPoint_updateOneClassicalPeriodById () {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ClassicalPeriod classicalPeriod = restTemplate.getForObject(BASE_URL+port+"/period/1", ClassicalPeriod.class);

        classicalPeriod.setName("Changed Renaissance");

        HttpEntity<ClassicalPeriod> httpEntity = new HttpEntity<>(classicalPeriod, headers);
        restTemplate.put(BASE_URL+port+"/period/"+classicalPeriod.getId(), httpEntity);

        ClassicalPeriod classicalPeriodReceived = restTemplate.getForObject(BASE_URL+port+"/period/1", ClassicalPeriod.class);

        Assertions.assertEquals("Changed Renaissance", classicalPeriodReceived.getName());

    }

    @Test
    public void testDeleteEndPoint_deletingOneClassicalPeriod () {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ClassicalPeriod> httpEntity = new HttpEntity<>(
                new ClassicalPeriod(0, "Modern", 1900, 2000, new ArrayList<>()), headers);
        ResponseEntity<String> postResponse = restTemplate.postForEntity(BASE_URL+port+"/period", httpEntity, String.class);
        Assertions.assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        ResponseEntity<ClassicalPeriod[]> responseEntityBeforeDeleting = restTemplate.getForEntity(BASE_URL+port+"/period", ClassicalPeriod[].class);
        List<ClassicalPeriod> beforeDeleting = Arrays.asList(responseEntityBeforeDeleting.getBody());
        Assertions.assertEquals(5, beforeDeleting.size());
        Assertions.assertEquals("Modern", beforeDeleting.get(4).getName());

        restTemplate.delete(BASE_URL+port+"/period/5");
        ResponseEntity<ClassicalPeriod[]> responseEntityAfterDeleting = restTemplate.getForEntity(BASE_URL+port+"/period", ClassicalPeriod[].class);
        List<ClassicalPeriod> afterDeleting = Arrays.asList(responseEntityAfterDeleting.getBody());
        Assertions.assertEquals(4, afterDeleting.size());
    }

//    @Test
//    public void testInvalidParameterException () {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<ClassicalPeriod> httpEntity = new HttpEntity<>(
//                new ClassicalPeriod(0, "Modern", 1900, 2100, new ArrayList<>()), headers);
//        ResponseEntity<Map<String, String>> responseEntity = restTemplate.exchange(
//                BASE_URL+port+"/period",
//                HttpMethod.POST,
//                httpEntity,
//                new ParameterizedTypeReference<>() {
//                }
//        );
//
//        Map<String, String> actual = responseEntity.getBody();
//
//        Assertions.assertEquals("End cannot be in the future!", actual.get("year_of_end"));
//    }

}

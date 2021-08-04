package com.codecool.classicalregistry.service;


import com.codecool.classicalregistry.controller.ClassicalPeriodController;
import com.codecool.classicalregistry.exceptions.DatabaseIsEmptyException;
import com.codecool.classicalregistry.model.ClassicalPeriod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collections;


@WebMvcTest(ClassicalPeriodController.class)
public class ClassicalPeriodUnitTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ClassicalPeriodService classicalPeriodService;

    ClassicalPeriod CP_1 = new ClassicalPeriod(1L, "Renaissance", 1400, 1600, Collections.emptyList());
    ClassicalPeriod CP_2 = new ClassicalPeriod(2L, "Baroque", 1600, 1750, Collections.emptyList());
    ClassicalPeriod CP_3 = new ClassicalPeriod(3L, "Classical", 1750, 1830, Collections.emptyList());

    @Test
    public void getAllPeriods_whenEmptyThrowsException () throws Exception {
        Mockito.when(classicalPeriodService.listAllClassicalPeriods()).thenThrow(new DatabaseIsEmptyException());

        mockMvc.perform(MockMvcRequestBuilders.
                        get("/period")).
                andExpect(mvcResult -> Assertions.assertTrue(mvcResult.getResolvedException() instanceof DatabaseIsEmptyException));


    }

    @Test
    public void getOnePeriodByID_whenNoEntryWithIdThrowsException() throws Exception {
        Mockito.when(classicalPeriodService.getClassicalPeriodById(4L)).thenThrow(new EmptyResultDataAccessException(0));

        mockMvc.perform(MockMvcRequestBuilders.get("/period/4")).
                andExpect(mvcResult -> Assertions.assertTrue(mvcResult.getResolvedException() instanceof EmptyResultDataAccessException));
    }

    @Test
    public void deleteOnePeriod_whenItIsNotPossibleThrowsException() throws Exception {
        Mockito.doThrow(new DataIntegrityViolationException("")).when(classicalPeriodService).deleteClassicalPeriodById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/period/1")).
                andExpect(mvcResult -> Assertions.assertTrue(mvcResult.getResolvedException() instanceof DataIntegrityViolationException));
    }
}

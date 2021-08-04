package com.codecool.classicalregistry.controller;


import com.codecool.classicalregistry.model.ClassicalPeriod;
import com.codecool.classicalregistry.service.ClassicalPeriodService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/period")
@ApiOperation(value = "Endpoints related to classical periods")
public class ClassicalPeriodController {

    @Autowired
    private ClassicalPeriodService classicalPeriodService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleException(MethodArgumentNotValidException methodArgumentNotValidException) {

        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping
    @ApiOperation(value = "This endpoint lists all the classical periods existing in the database")
    public List<ClassicalPeriod> listAllClassicalPeriods() {
        return classicalPeriodService.listAllClassicalPeriods();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "This endpoint shows the classical period which has the same ID passed as a parameter")
    public ClassicalPeriod getClassicalPeriodById (@PathVariable long id) {
        return classicalPeriodService.getClassicalPeriodById(id);
    }

    @PostMapping
    @ApiOperation(value = "This endpoint adds a new classical period to the database")
    public ResponseEntity<String> addClassicalPeriod(@Valid @RequestBody ClassicalPeriod classicalPeriod) {
            classicalPeriodService.addClassicalPeriod(classicalPeriod);
            return ResponseEntity.ok("Entity successfully added!");

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "This endpoint updates the classical period if it exists in the database, or adds it, if it does not")
    public ResponseEntity<String> updateClassicalPeriodById(@Valid @RequestBody ClassicalPeriod classicalPeriod, @PathVariable long id) {
        classicalPeriodService.updateClassicalPeriodById(classicalPeriod, id);
        return ResponseEntity.ok("Entity has been successfully updated!");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "This endpoint deletes one classical period which has the same ID passed in as a parameter from the database")
    public ResponseEntity<String> deleteClassicalPeriodById(@PathVariable long id) {
       classicalPeriodService.deleteClassicalPeriodById(id);
       return ResponseEntity.ok("Entity has been successfully deleted!");
    }
}

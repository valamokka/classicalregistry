package com.codecool.classicalregistry.controller;


import com.codecool.classicalregistry.model.DTO.CompositionDTO;
import com.codecool.classicalregistry.service.CompositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/composition")
public class CompositionController {

    @Autowired
    private CompositionService compositionService;

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
    @ApiOperation(value = "This endpoint lists all the compositions existing in the database")
    public List<CompositionDTO> listAllCompositions() {
        return compositionService.listAllCompositions();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "This endpoint shows the composition which has the same ID passed as a parameter")
    public CompositionDTO getCompositionById(@PathVariable long id) {
        return compositionService.getCompositionById(id);
    }

    @PostMapping
    @ApiOperation(value = "This endpoint adds a new composition to the database")
    public ResponseEntity<String> addComposition(@Valid @RequestBody CompositionDTO compositionDTO) {
        compositionService.addComposition(compositionDTO);
        return ResponseEntity.ok("Entity successfully added!");
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "This endpoint updates the composition if it exists in the database, or adds it, if it does not")
    public ResponseEntity<String> updateCompositionById(@Valid @RequestBody CompositionDTO compositionDTO, @PathVariable long id) {
        compositionService.updateCompositionById(compositionDTO, id);
        return ResponseEntity.ok("Entity has been successfully updated!");
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "This endpoint deletes one composition which has the same ID passed in as a parameter from the database")
    public ResponseEntity<String> deleteCompositionById(@PathVariable long id) {
        compositionService.deleteCompositionById(id);
        return ResponseEntity.ok("Entity has been successfully deleted!");
    }

}

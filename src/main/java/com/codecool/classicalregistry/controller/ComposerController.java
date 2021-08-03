package com.codecool.classicalregistry.controller;


import com.codecool.classicalregistry.model.DTO.ComposerDTO;
import com.codecool.classicalregistry.model.DTO.CompositionDTO;
import com.codecool.classicalregistry.service.ComposerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/composer")
public class ComposerController {

    @Autowired
    private ComposerService composerService;

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
    @ApiOperation(value = "This endpoint lists all the composers existing in the database")
    public List<ComposerDTO> listAllComposers() {
        return composerService.listAllComposers();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "This endpoint shows the composer which has the same ID passed as a parameter")
    public ComposerDTO getComposerById(@PathVariable long id) {
        return composerService.getComposerById(id);
    }

    @PostMapping
    @ApiOperation(value = "This endpoint adds a new composer to the database")
    public void addComposer(@Valid @RequestBody ComposerDTO composerDTO) {
        composerService.addComposer(composerDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "This endpoint updates the composer if it exists in the database, or adds it, if it does not")
    public void updateComposerById(@Valid @RequestBody ComposerDTO composerDTO, @PathVariable long id) {
        composerService.updateComposerById(composerDTO, id);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "This endpoint deletes one composer which has the same ID passed in as a parameter from the database")
    public void deleteComposerById(@PathVariable long id) {
        composerService.deleteComposerById(id);
    }

    @GetMapping("/{id}/composition")
    @ApiOperation(value = "This endpoints lists all the compositions for a composer, whose ID has been passed as a parameter")
    public List<CompositionDTO> findAllCompositionsByComposerId (@PathVariable long id) {
        return composerService.findAllCompositionsByComposerId(id);
    }
}

package com.codecool.classicalregistry.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntryNotFoundException.class})
    public ResponseEntity<String> handleEntryNotFound() {
        return ResponseEntity.badRequest().body("There is no entry in the database with this id!");
    }

    @ExceptionHandler(value = {DatabaseIsEmptyException.class})
    public ResponseEntity<String> handleEmptyDatabase() {
        return ResponseEntity.badRequest().body("There are no entries in the database!");
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleDatabaseIntegrityViolation() {
        return ResponseEntity.badRequest().body("You cannot delete this entity, because other entities are depending on it");
    }

    @ExceptionHandler(value = EntityDependencyException.class)
    public ResponseEntity<String> handleEntityDependency() {
        return ResponseEntity.badRequest().body("You cannot add or update this entity with an unexisting composer id or classical period id, " +
                "first create the necessary composer id or classical period id, or choose another id!");
    }

}

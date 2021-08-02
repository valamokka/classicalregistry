package com.codecool.classicalregistry.exceptions;

public class EntityDependencyException extends RuntimeException {

    public EntityDependencyException() {
    }

    public EntityDependencyException(String message) {
        super(message);
    }
}

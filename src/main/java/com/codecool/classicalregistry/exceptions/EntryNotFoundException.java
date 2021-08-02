package com.codecool.classicalregistry.exceptions;

public class EntryNotFoundException extends RuntimeException {

    public EntryNotFoundException() {
    }

    public EntryNotFoundException(String message) {
        super(message);
    }
}

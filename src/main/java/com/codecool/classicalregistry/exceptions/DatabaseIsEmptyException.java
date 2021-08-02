package com.codecool.classicalregistry.exceptions;

public class DatabaseIsEmptyException extends  RuntimeException {

    public DatabaseIsEmptyException() {
    }

    public DatabaseIsEmptyException(String message) {
        super(message);
    }
}

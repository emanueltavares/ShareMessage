package com.emanueltavares.module.exception;

public class InvalidMessageException extends Exception {

    private String message;

    public InvalidMessageException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return String.format("%s is an invalid message!", message);
    }
}

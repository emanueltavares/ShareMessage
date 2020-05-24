package com.emanueltavares.module.exception;

public class InvalidUserIdException extends Exception {

    private String userId;

    public InvalidUserIdException(String userId) {
        this.userId = userId;
    }
    @Override
    public String getMessage() {
        return String.format("%s is an invalid user id!", userId);
    }

}

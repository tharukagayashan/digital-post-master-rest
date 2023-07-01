package com.projects.digitalpostmasterrest.error;

public class ErrorAlert extends RuntimeException{
    private final String message;
    private final String errorKey;

    public ErrorAlert(String message, String errorKey) {
        this.message = message;
        this.errorKey = errorKey;
    }
}

package com.projects.digitalpostmasterrest.error;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorDto extends RuntimeException{
    private final String message;
    private final String errorKey;
}

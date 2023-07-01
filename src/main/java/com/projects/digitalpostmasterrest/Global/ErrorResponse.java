package com.projects.digitalpostmasterrest.Global;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int httpCode;
    private String errorCode;
    private String description;
}

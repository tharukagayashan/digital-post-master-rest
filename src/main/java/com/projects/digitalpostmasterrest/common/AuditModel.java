package com.projects.digitalpostmasterrest.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuditModel {
    private LocalDate createdDate = LocalDate.now();
    private LocalTime createdTime = LocalTime.now();
    private String createdBy = "Admin User";
}

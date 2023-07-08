package com.projects.digitalpostmasterrest.enums;

import org.springframework.web.bind.annotation.RequestMapping;

public enum StatusEnum {

    ACTIVE,
    COMPLETED,
    DELIVERED,
    FAILED,
    PAID,
    PENDING,
    PICKUPED,
    SHIPPED,
    NEW;
}
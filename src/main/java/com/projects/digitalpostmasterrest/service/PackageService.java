package com.projects.digitalpostmasterrest.service;

import com.projects.digitalpostmasterrest.dto.custom.PackageCreateReqDto;
import org.springframework.http.ResponseEntity;

public interface PackageService {
    ResponseEntity createPackage(PackageCreateReqDto packageCreateReqDto);
}

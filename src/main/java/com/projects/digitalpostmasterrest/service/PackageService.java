package com.projects.digitalpostmasterrest.service;

import com.projects.digitalpostmasterrest.dto.PackageDetailDto;
import com.projects.digitalpostmasterrest.dto.custom.PackageCreateReqDto;
import org.springframework.http.ResponseEntity;

public interface PackageService {
    ResponseEntity createPackage(PackageCreateReqDto packageCreateReqDto);
    ResponseEntity getAllPackages();

    ResponseEntity updatePackage(PackageDetailDto packageDetailDto);
}

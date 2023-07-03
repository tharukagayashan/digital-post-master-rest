package com.projects.digitalpostmasterrest.service;

import com.projects.digitalpostmasterrest.dto.PackageDetailDto;
import com.projects.digitalpostmasterrest.dto.custom.PackageCreateReqDto;
import org.springframework.http.ResponseEntity;

public interface PackageService {
    ResponseEntity createPackage(PackageCreateReqDto packageCreateReqDto);
    ResponseEntity getAllPackages(String status);
    ResponseEntity updatePackage(PackageDetailDto packageDetailDto);
    ResponseEntity deletePackage(Integer packageId);
    ResponseEntity getPackageById(Integer packageId);
    ResponseEntity getPackagesByUserId(Integer userId);
}

package com.projects.digitalpostmasterrest.rest;

import com.projects.digitalpostmasterrest.dto.PackageDto;
import com.projects.digitalpostmasterrest.dto.custom.PackageCreateReqDto;
import com.projects.digitalpostmasterrest.service.PackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/package")
public class PackageController{

    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping("/create")
    public ResponseEntity<PackageDto> createPackage(@RequestBody PackageCreateReqDto packageCreateReqDto){
        ResponseEntity response = packageService.createPackage(packageCreateReqDto);
        return response;
    }

}

package com.projects.digitalpostmasterrest.rest;

import com.projects.digitalpostmasterrest.dto.PackageDto;
import com.projects.digitalpostmasterrest.dto.custom.PackageCreateReqDto;
import com.projects.digitalpostmasterrest.service.PackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/package-data")
public class PackageController{

    private final PackageService packageService;

    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping("/create")
    public ResponseEntity<PackageDto> createPackage(@Valid @RequestBody PackageCreateReqDto packageCreateReqDto){
        ResponseEntity response = packageService.createPackage(packageCreateReqDto);
        return response;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<PackageDto>> getAllPackages(){
        ResponseEntity response = packageService.getAllPackages();
        return response;
    }

}

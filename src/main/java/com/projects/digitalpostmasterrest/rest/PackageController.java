package com.projects.digitalpostmasterrest.rest;

import com.projects.digitalpostmasterrest.dto.PackageDetailDto;
import com.projects.digitalpostmasterrest.dto.custom.PackageCreateReqDto;
import com.projects.digitalpostmasterrest.model.PackageDetail;
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
    public ResponseEntity<PackageDetailDto> createPackage(@Valid @RequestBody PackageCreateReqDto packageCreateReqDto){
        ResponseEntity response = packageService.createPackage(packageCreateReqDto);
        return response;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<PackageDetail>> getAllPackages(){
        ResponseEntity response = packageService.getAllPackages();
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<PackageDetailDto> updatePackage(@RequestBody PackageDetailDto packageDetailDto){
        ResponseEntity response = packageService.updatePackage(packageDetailDto);
        return response;
    }

}

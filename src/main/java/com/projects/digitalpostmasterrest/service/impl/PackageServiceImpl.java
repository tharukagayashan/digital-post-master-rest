package com.projects.digitalpostmasterrest.service.impl;

import com.projects.digitalpostmasterrest.dao.PackageDao;
import com.projects.digitalpostmasterrest.dto.PackageDto;
import com.projects.digitalpostmasterrest.dto.custom.PackageCreateReqDto;
import com.projects.digitalpostmasterrest.error.ErrorAlert;
import com.projects.digitalpostmasterrest.model.Package;
import com.projects.digitalpostmasterrest.service.PackageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.projects.digitalpostmasterrest.constant.Contants.*;

@Slf4j
@Service
public class PackageServiceImpl implements PackageService {

    private final PackageDao packageDao;

    public PackageServiceImpl(PackageDao packageDao) {
        this.packageDao = packageDao;
    }

    @Override
    public ResponseEntity createPackage(PackageCreateReqDto packageCreateReqDto) {
        try {

            String sender = packageCreateReqDto.getSender();
            String receiver = packageCreateReqDto.getReceiver();
            String receiverAddress = packageCreateReqDto.getReceiverAddress();
            Float length = packageCreateReqDto.getLength();
            Float width = packageCreateReqDto.getWidth();
            Float height = packageCreateReqDto.getHeight();

            String dimensions = "L:" + length + " W:" + width + " H:" + height;

            if (sender.isEmpty()) {
                throw new ErrorAlert(SENDER_EMPTY, "400");
            } else if (receiver.isEmpty()) {
                throw new ErrorAlert(RECEIVER_EMPTY, "400");
            } else if (receiverAddress.isEmpty()) {
                throw new ErrorAlert(RECEIVER_ADDRESS_EMPTY, "400");
            } else {
                log.info("Required properties are found!!!");
            }

            Package newPackage = new Package();
            newPackage.setSender(sender);
            newPackage.setReceiver(receiver);
            newPackage.setReceiverAddress(receiverAddress);
            newPackage.setDimensions(dimensions);
            newPackage.setWeight(packageCreateReqDto.getWeight());
            newPackage.setInstructions(packageCreateReqDto.getInstructions());

            newPackage = packageDao.save(newPackage);
            if (newPackage != null) {
                return ResponseEntity.ok(newPackage.toDto());
            } else {
                throw new ErrorAlert(PACKAGE_CREATE_ERROR, "400");
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity getAllPackages() {
        try {

            List<Package> packages = new ArrayList<>();
            if (packages.isEmpty()) {
                throw new ErrorAlert(PACKAGE_LIST_EMPTY, "400");
            } else {
                List<PackageDto> packageList = new ArrayList<>();
                for (Package p : packages) {
                    packageList.add(p.toDto());
                }
                return ResponseEntity.ok(packageList);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }
}

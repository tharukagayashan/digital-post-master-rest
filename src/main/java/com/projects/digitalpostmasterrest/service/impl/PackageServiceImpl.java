package com.projects.digitalpostmasterrest.service.impl;

import com.projects.digitalpostmasterrest.dao.PackageDao;
import com.projects.digitalpostmasterrest.dto.PackageDetailDto;
import com.projects.digitalpostmasterrest.dto.custom.PackageCreateReqDto;
import com.projects.digitalpostmasterrest.error.ErrorAlert;
import com.projects.digitalpostmasterrest.model.PackageDetail;
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

            PackageDetail newPackageDetail = new PackageDetail();
            newPackageDetail.setSender(sender);
            newPackageDetail.setReceiver(receiver);
            newPackageDetail.setReceiverAddress(receiverAddress);
            newPackageDetail.setDimensions(dimensions);
            newPackageDetail.setWeight(packageCreateReqDto.getWeight());
            newPackageDetail.setInstructions(packageCreateReqDto.getInstructions());

            newPackageDetail = packageDao.save(newPackageDetail);
            if (newPackageDetail != null) {
                return ResponseEntity.ok(newPackageDetail.toDto());
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

            List<PackageDetail> packageDetails = packageDao.findAll();
            List<PackageDetailDto> packageDtoList = new ArrayList<>();
            if (packageDetails.isEmpty()) {
                throw new ErrorAlert(PACKAGE_LIST_EMPTY, "400");
            } else {
                for (PackageDetail p : packageDetails) {
                    packageDtoList.add(p.toDto());
                }
                return ResponseEntity.ok(packageDetails);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }
}

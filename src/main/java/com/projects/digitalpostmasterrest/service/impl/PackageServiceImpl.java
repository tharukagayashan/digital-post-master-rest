package com.projects.digitalpostmasterrest.service.impl;

import com.projects.digitalpostmasterrest.dao.PackageDetailDao;
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
import java.util.Optional;

import static com.projects.digitalpostmasterrest.constant.Contants.*;

@Slf4j
@Service
public class PackageServiceImpl implements PackageService {

    private final PackageDetailDao packageDetailDao;

    public PackageServiceImpl(PackageDetailDao packageDetailDao) {
        this.packageDetailDao = packageDetailDao;
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

            newPackageDetail = packageDetailDao.save(newPackageDetail);
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

            List<PackageDetail> packageDetails = packageDetailDao.findAll();
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

    @Override
    public ResponseEntity updatePackage(PackageDetailDto packageDetailDto) {
        try {
            if (packageDetailDto.getPackageId() == null) {
                log.error(PACKAGE_ID_NULL);
                throw new ErrorAlert(PACKAGE_ID_NULL, "400");
            } else {
                Optional<PackageDetail> optPackage = packageDetailDao.findById(packageDetailDto.getPackageId());
                if (!optPackage.isPresent()) {
                    log.error(PACKAGE_NOT_FOUND);
                    throw new ErrorAlert(PACKAGE_NOT_FOUND, "400");
                } else {
                    PackageDetail packageDetail = optPackage.get();
                    packageDetail.setDimensions(packageDetailDto.getDimensions());
                    packageDetail.setSender(packageDetailDto.getSender());
                    packageDetail.setInstructions(packageDetailDto.getInstructions());
                    packageDetail.setWeight(packageDetailDto.getWeight());
                    packageDetail.setReceiver(packageDetailDto.getReceiver());
                    packageDetail.setReceiverAddress(packageDetailDto.getReceiverAddress());

                    packageDetail = packageDetailDao.save(packageDetail);
                    return ResponseEntity.ok(packageDetail.toDto());
                }
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }
}

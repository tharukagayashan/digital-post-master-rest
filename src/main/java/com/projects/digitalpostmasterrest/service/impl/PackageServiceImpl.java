package com.projects.digitalpostmasterrest.service.impl;

import com.projects.digitalpostmasterrest.common.MailService;
import com.projects.digitalpostmasterrest.dao.PackageDetailDao;
import com.projects.digitalpostmasterrest.dao.UserDetailDao;
import com.projects.digitalpostmasterrest.dto.PackageDetailDto;
import com.projects.digitalpostmasterrest.dto.custom.MailReqDto;
import com.projects.digitalpostmasterrest.dto.custom.PackageCreateReqDto;
import com.projects.digitalpostmasterrest.enums.StatusEnum;
import com.projects.digitalpostmasterrest.error.ErrorAlert;
import com.projects.digitalpostmasterrest.model.PackageDetail;
import com.projects.digitalpostmasterrest.model.UserDetail;
import com.projects.digitalpostmasterrest.service.PackageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.projects.digitalpostmasterrest.constant.Constants.*;

@Slf4j
@Service
public class PackageServiceImpl implements PackageService {
    private final UserDetailDao userDetailDao;

    private final PackageDetailDao packageDetailDao;
    private final JavaMailSender mailSender;
    private final MailService mailService;

    public PackageServiceImpl(PackageDetailDao packageDetailDao,
                              UserDetailDao userDetailDao, JavaMailSender mailSender, MailService mailService) {
        this.packageDetailDao = packageDetailDao;
        this.userDetailDao = userDetailDao;
        this.mailSender = mailSender;
        this.mailService = mailService;
    }

    @Transactional
    @Override
    public ResponseEntity createPackage(PackageCreateReqDto packageCreateReqDto) {
        try {

            Integer userId = packageCreateReqDto.getUserId();
            String receiver = packageCreateReqDto.getReceiver();
            String receiverAddress = packageCreateReqDto.getReceiverAddress();
            Float length = packageCreateReqDto.getLength();
            Float width = packageCreateReqDto.getWidth();
            Float height = packageCreateReqDto.getHeight();

            String dimensions = "L:" + length + " W:" + width + " H:" + height;

            if (userId == null) {
                throw new ErrorAlert(USER_ID_NULL, "400");
            } else if (receiver.isEmpty()) {
                throw new ErrorAlert(RECEIVER_EMPTY, "400");
            } else if (receiverAddress.isEmpty()) {
                throw new ErrorAlert(RECEIVER_ADDRESS_EMPTY, "400");
            } else {
                log.info("Required properties are found!!!");
            }

            Optional<UserDetail> optUserDetails = userDetailDao.findById(userId);
            if (!optUserDetails.isPresent()) {
                throw new ErrorAlert(USER_NOT_FOUND, "400");
            }

            UserDetail userDetail = optUserDetails.get();

            PackageDetail newPackageDetail = new PackageDetail();
            newPackageDetail.setUserDetail(userDetail);
            newPackageDetail.setReceiver(receiver);
            newPackageDetail.setReceiverAddress(receiverAddress);
            newPackageDetail.setDimensions(dimensions);
            newPackageDetail.setWeight(packageCreateReqDto.getWeight());
            newPackageDetail.setInstructions(packageCreateReqDto.getInstructions());
            newPackageDetail.setStatus(StatusEnum.NEW.name());

            newPackageDetail = packageDetailDao.save(newPackageDetail);
            MailReqDto mailReqDto = new MailReqDto();
            mailReqDto.setTo(userDetail.getEmail());
            mailReqDto.setSubject(PACKAGE_CREATE_MAIL_SUBJECT);
            mailReqDto.setBody(PACKAGE_CREATE_MAIL_BODY);
            mailService.sendMail(mailSender,mailReqDto);

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
    public ResponseEntity getAllPackages(String status) {
        try {
            List<PackageDetailDto> packageDtoList = new ArrayList<>();
            if (status.equalsIgnoreCase("")) {
                List<PackageDetail> packageDetails = packageDetailDao.findAll();
                if (packageDetails.isEmpty()) {
                    throw new ErrorAlert(PACKAGE_LIST_EMPTY, "400");
                } else {
                    for (PackageDetail p : packageDetails) {
                        packageDtoList.add(p.toDto());
                    }
                    return ResponseEntity.ok(packageDtoList);
                }
            } else {
                List<PackageDetail> packageDetails = packageDetailDao
                        .findAll()
                        .stream()
                        .filter(pack ->
                                pack.getStatus()
                                        .equalsIgnoreCase(status)).collect(Collectors.toList()
                        );
                if (packageDetails.isEmpty()) {
                    throw new ErrorAlert(PACKAGE_LIST_EMPTY, "400");
                } else {
                    for (PackageDetail p : packageDetails) {
                        packageDtoList.add(p.toDto());
                    }
                    return ResponseEntity.ok(packageDtoList);
                }
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
                if (packageDetailDto.getUserId() == null) {
                    throw new ErrorAlert(USER_ID_NULL, "400");
                }
                Optional<UserDetail> optUser = userDetailDao.findById(packageDetailDto.getUserId());
                if (!optUser.isPresent()) {
                    throw new ErrorAlert(USER_NOT_FOUND, "400");
                }
                Optional<PackageDetail> optPackage = packageDetailDao.findById(packageDetailDto.getPackageId());
                if (!optPackage.isPresent()) {
                    log.error(PACKAGE_NOT_FOUND);
                    throw new ErrorAlert(PACKAGE_NOT_FOUND, "400");
                } else {
                    PackageDetail packageDetail = optPackage.get();
                    packageDetail.setDimensions(packageDetailDto.getDimensions());
                    packageDetail.setUserDetail(optUser.get());
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

    @Override
    public ResponseEntity deletePackage(Integer packageId) {
        try {
            if (packageId == null) {
                throw new ErrorAlert(PACKAGE_ID_NULL, "400");
            } else {
                Optional<PackageDetail> optPackage = packageDetailDao.findById(packageId);
                if (!optPackage.isPresent()) {
                    throw new ErrorAlert(PACKAGE_NOT_FOUND, "400");
                } else {
                    packageDetailDao.deleteById(packageId);
                    Optional<PackageDetail> check = packageDetailDao.findById(packageId);
                    if (!check.isPresent()) {
                        return ResponseEntity.ok(packageId);
                    } else {
                        throw new ErrorAlert(PACKAGE_DELETE_ERROR, "400");
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity getPackageById(Integer packageId) {
        try {
            if (packageId == null) {
                throw new ErrorAlert(PACKAGE_ID_NULL, "400");
            } else {
                Optional<PackageDetail> optPackage = packageDetailDao.findById(packageId);
                if (!optPackage.isPresent()) {
                    throw new ErrorAlert(PACKAGE_NOT_FOUND, "400");
                } else {
                    return ResponseEntity.ok(optPackage.get().toDto());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity getPackagesByUserId(Integer userId) {
        try {
            if (userId == null) {
                throw new ErrorAlert(USER_ID_NULL, "400");
            } else {

                Optional<UserDetail> optUserDetails = userDetailDao.findById(userId);
                if (!optUserDetails.isPresent()) {
                    throw new ErrorAlert(USER_NOT_FOUND, "400");
                }

                List<PackageDetail> packageDetailList = packageDetailDao.findAll()
                        .stream()
                        .filter(pack ->
                                pack.getUserDetail()
                                        .getUserId() == userId).collect(Collectors.toList()
                        );
                if (packageDetailList.isEmpty()) {
                    throw new ErrorAlert(PACKAGE_NOT_FOUND, "400");
                } else {
                    List<PackageDetailDto> packageDetailDtoList = new ArrayList<>();
                    for (PackageDetail p : packageDetailList) {
                        packageDetailDtoList.add(p.toDto());
                    }
                    return ResponseEntity.ok(packageDetailDtoList);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

}
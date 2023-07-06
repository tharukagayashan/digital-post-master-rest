package com.projects.digitalpostmasterrest.service.impl;

import com.projects.digitalpostmasterrest.dao.PackageDetailDao;
import com.projects.digitalpostmasterrest.dao.PaymentDao;
import com.projects.digitalpostmasterrest.dao.UserDetailDao;
import com.projects.digitalpostmasterrest.dto.PaymentDto;
import com.projects.digitalpostmasterrest.dto.custom.PaymentCreateReqDto;
import com.projects.digitalpostmasterrest.enums.StatusEnum;
import com.projects.digitalpostmasterrest.error.ErrorAlert;
import com.projects.digitalpostmasterrest.model.PackageDetail;
import com.projects.digitalpostmasterrest.model.Payment;
import com.projects.digitalpostmasterrest.model.UserDetail;
import com.projects.digitalpostmasterrest.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.projects.digitalpostmasterrest.constant.Constants.*;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PackageDetailDao packageDao;
    private final UserDetailDao userDao;
    private final PaymentDao paymentDao;

    public PaymentServiceImpl(PaymentDao paymentDao,
                              UserDetailDao userDao,
                              PackageDetailDao packageDao) {
        this.paymentDao = paymentDao;
        this.userDao = userDao;
        this.packageDao = packageDao;
    }

    @Override
    public ResponseEntity createPayment(PaymentCreateReqDto paymentCreateReqDto) {
        try {

            Optional<UserDetail> optUser = userDao.findById(paymentCreateReqDto.getUserId());
            Optional<PackageDetail> optPackage = packageDao.findById(paymentCreateReqDto.getPackageId());

            UserDetail user = new UserDetail();
            PackageDetail packageDetail = new PackageDetail();
            if (!optUser.isPresent()) {
                throw new ErrorAlert(USER_NOT_FOUND, "400");
            } else {
                user = optUser.get();
            }
            if (!optPackage.isPresent()) {
                throw new ErrorAlert(PACKAGE_NOT_FOUND, "400");
            } else {
                packageDetail = optPackage.get();
            }

            Payment payment = new Payment();
            payment.setAmount(paymentCreateReqDto.getAmount());
            payment.setStatus(StatusEnum.NEW.name());
            payment.setTime(LocalDateTime.now());
            payment.setUserDetail(user);
            payment.setPackageDetail(packageDetail);

            payment = paymentDao.save(payment);
            if (payment == null) {
                log.error(PAYMENT_CREATE_ERROR);
                throw new ErrorAlert(PAYMENT_CREATE_ERROR, "400");
            } else {
                return ResponseEntity.ok(payment.toDto());
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity getAllPayments() {
        try {
            List<PaymentDto> paymentDtoList = new ArrayList<>();
            List<Payment> paymentList = paymentDao.findAll();
            if (paymentList.isEmpty()) {
                throw new ErrorAlert(PAYMENT_LIST_EMPTY, "400");
            } else {
                for (Payment p : paymentList) {
                    paymentDtoList.add(p.toDto());
                }
                return ResponseEntity.ok(paymentDtoList);
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }
}

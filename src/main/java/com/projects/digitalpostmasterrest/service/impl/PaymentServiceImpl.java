package com.projects.digitalpostmasterrest.service.impl;

import com.projects.digitalpostmasterrest.common.MailService;
import com.projects.digitalpostmasterrest.dao.PackageDetailDao;
import com.projects.digitalpostmasterrest.dao.PaymentDao;
import com.projects.digitalpostmasterrest.dao.UserDetailDao;
import com.projects.digitalpostmasterrest.dto.PaymentDto;
import com.projects.digitalpostmasterrest.dto.custom.MailReqDto;
import com.projects.digitalpostmasterrest.dto.custom.PaymentCreateReqDto;
import com.projects.digitalpostmasterrest.enums.StatusEnum;
import com.projects.digitalpostmasterrest.error.ErrorAlert;
import com.projects.digitalpostmasterrest.model.PackageDetail;
import com.projects.digitalpostmasterrest.model.Payment;
import com.projects.digitalpostmasterrest.model.UserDetail;
import com.projects.digitalpostmasterrest.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
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
    private final MailService mailService;
    private final JavaMailSender mailSender;

    public PaymentServiceImpl(PaymentDao paymentDao,
                              UserDetailDao userDao,
                              PackageDetailDao packageDao, MailService mailService, JavaMailSender mailSender) {
        this.paymentDao = paymentDao;
        this.userDao = userDao;
        this.packageDao = packageDao;
        this.mailService = mailService;
        this.mailSender = mailSender;
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

            if (paymentCreateReqDto.getAmount() == null || paymentCreateReqDto.getAmount() <= 0) {
                throw new ErrorAlert(PAYMENT_AMOUNT_ERROR, "400");
            }

            Payment payment = new Payment();
            payment.setAmount(paymentCreateReqDto.getAmount());
            payment.setStatus(StatusEnum.NEW.name());
            payment.setTime(LocalDateTime.now());
            payment.setUserDetail(user);
            payment.setPackageDetail(packageDetail);

            MailReqDto mailReqDto = new MailReqDto();
            mailReqDto.setTo(user.getEmail());
            mailReqDto.setSubject(PAYMENT_CREATE_MAIL_SUBJECT);
            mailReqDto.setBody(PAYMENT_CREATE_MAIL_BODY);
            mailService.sendMail(mailSender, mailReqDto);

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

    @Override
    public ResponseEntity approvePayment(Integer paymentId) {
        try {
            if (paymentId == null) {
                throw new ErrorAlert(PAYMENT_ID_NULL, "400");
            } else {
                Optional<Payment> optPayment = paymentDao.findById(paymentId);
                if (!optPayment.isPresent()) {
                    throw new ErrorAlert(PAYMENT_NOT_FOUND, "400");
                } else {
                    Payment payment = optPayment.get();
                    payment.setStatus(StatusEnum.APPROVE.name());
                    payment = paymentDao.save(payment);
                    return ResponseEntity.ok(payment.toDto());
                }
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }
}

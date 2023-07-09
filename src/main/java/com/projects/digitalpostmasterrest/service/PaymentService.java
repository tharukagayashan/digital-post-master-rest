package com.projects.digitalpostmasterrest.service;

import com.projects.digitalpostmasterrest.dto.custom.PaymentCreateReqDto;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ResponseEntity createPayment(PaymentCreateReqDto paymentCreateReqDto);
    ResponseEntity getAllPayments();

    ResponseEntity approvePayment(Integer paymentId);
}

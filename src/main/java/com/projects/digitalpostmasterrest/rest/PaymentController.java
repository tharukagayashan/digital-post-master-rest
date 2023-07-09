package com.projects.digitalpostmasterrest.rest;

import com.projects.digitalpostmasterrest.dto.PaymentDto;
import com.projects.digitalpostmasterrest.dto.custom.PaymentCreateReqDto;
import com.projects.digitalpostmasterrest.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentDto> createPayment(@Valid @RequestBody PaymentCreateReqDto paymentCreateReqDto) {
        ResponseEntity response = paymentService.createPayment(paymentCreateReqDto);
        return response;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<PaymentDto>> getAllPayments() {
        ResponseEntity response = paymentService.getAllPayments();
        return response;
    }

    @PutMapping("/approve/{paymentId}")
    public ResponseEntity<PaymentDto> approvePayment(@PathVariable Integer paymentId) {
        ResponseEntity response = paymentService.approvePayment(paymentId);
        return response;
    }

}

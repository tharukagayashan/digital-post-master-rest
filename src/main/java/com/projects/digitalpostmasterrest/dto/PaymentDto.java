package com.projects.digitalpostmasterrest.dto;

import com.projects.digitalpostmasterrest.common.AuditModel;
import com.projects.digitalpostmasterrest.dao.PackageDetailDao;
import com.projects.digitalpostmasterrest.dao.UserDetailDao;
import com.projects.digitalpostmasterrest.model.PackageDetail;
import com.projects.digitalpostmasterrest.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto extends AuditModel {
    private Integer paymentId;
    private Float amount;
    private String status;
    private LocalDateTime time;

    private Integer userId;
    private Integer packageId;

    public Payment toEntity() {

        UserDetailDao userDetailDao = null;
        PackageDetailDao packageDetailDao = null;

        Payment payment = new Payment();
        payment.setPaymentId(this.getPaymentId());
        payment.setAmount(this.getAmount());
        payment.setStatus(this.getStatus());
        payment.setTime(this.getTime());
        payment.setUserDetail(userDetailDao.findById(this.userId).get());
        payment.setPackageDetail(packageDetailDao.findById(this.getPackageId()).get());
        payment.setCreatedDate(this.getCreatedDate());
        payment.setCreatedBy(this.getCreatedBy());
        return payment;
    }

}

package com.projects.digitalpostmasterrest.model;

import com.projects.digitalpostmasterrest.common.AuditModel;
import com.projects.digitalpostmasterrest.dto.PaymentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PAYMENT")
public class Payment extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PAYMENT_ID")
    private Integer paymentId;
    @Column(name = "AMOUNT")
    private Float amount;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "TIME")
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    private UserDetail userDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PACKAGE_ID", referencedColumnName = "PACKAGE_ID", nullable = false)
    private PackageDetail packageDetail;

    public PaymentDto toDto() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId(this.getPaymentId());
        paymentDto.setAmount(this.getAmount());
        paymentDto.setStatus(this.getStatus());
        paymentDto.setTime(this.getTime());
        paymentDto.setUserId(this.getUserDetail().getUserId());
        paymentDto.setPackageId(this.getPackageDetail().getPackageId());
        paymentDto.setCreatedDate(this.getCreatedDate());
        paymentDto.setCreatedBy(this.getCreatedBy());
        return paymentDto;
    }
}

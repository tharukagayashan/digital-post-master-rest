package com.projects.digitalpostmasterrest.model;

import com.projects.digitalpostmasterrest.common.AuditModel;
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
    @JoinColumn(name = "USER_ID",referencedColumnName = "USER_ID",nullable = false)
    private UserDetail userDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PACKAGE_ID",referencedColumnName = "PACKAGE_ID",nullable = false)
    private PackageDetail packageDetail;



}

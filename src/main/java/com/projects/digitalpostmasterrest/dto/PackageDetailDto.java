package com.projects.digitalpostmasterrest.dto;

import com.projects.digitalpostmasterrest.common.AuditModel;
import com.projects.digitalpostmasterrest.model.PackageDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageDetailDto extends AuditModel {

    private Integer packageId;
    private String sender;
    private String receiver;
    private String receiverAddress;
    private String dimensions;
    private Float weight;
    private String instructions;

    public PackageDetail toEntity() {
        PackageDetail newPackageDetail = new PackageDetail();
        newPackageDetail.setPackageId(this.getPackageId());
        newPackageDetail.setSender(this.getSender());
        newPackageDetail.setReceiver(this.getReceiver());
        newPackageDetail.setReceiverAddress(this.getReceiverAddress());
        newPackageDetail.setDimensions(this.getDimensions());
        newPackageDetail.setWeight(this.getWeight());
        newPackageDetail.setInstructions(this.getInstructions());
        newPackageDetail.setCreatedDate(this.getCreatedDate());
        newPackageDetail.setCreatedTime(this.getCreatedTime());
        newPackageDetail.setCreatedBy(this.getCreatedBy());
        return newPackageDetail;
    }

}

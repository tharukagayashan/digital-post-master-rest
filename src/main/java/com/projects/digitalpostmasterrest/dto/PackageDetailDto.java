package com.projects.digitalpostmasterrest.dto;

import com.projects.digitalpostmasterrest.common.AuditModel;
import com.projects.digitalpostmasterrest.dao.UserDetailDao;
import com.projects.digitalpostmasterrest.model.PackageDetail;
import com.projects.digitalpostmasterrest.model.UserDetail;
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
    private String receiver;
    private String receiverAddress;
    private String dimensions;
    private Float weight;
    private String instructions;
    private Integer userId; //sender
    private String status;

    public PackageDetail toEntity() {

        UserDetailDao userDetailDao = null;
        
        PackageDetail newPackageDetail = new PackageDetail();
        newPackageDetail.setPackageId(this.packageId);
        newPackageDetail.setUserDetail(userDetailDao.findById(userId).get());
        newPackageDetail.setReceiver(this.receiver);
        newPackageDetail.setReceiverAddress(this.receiverAddress);
        newPackageDetail.setDimensions(this.dimensions);
        newPackageDetail.setWeight(this.weight);
        newPackageDetail.setInstructions(this.instructions);
        newPackageDetail.setStatus(this.status);
        newPackageDetail.setCreatedDate(this.getCreatedDate());
        newPackageDetail.setCreatedBy(this.getCreatedBy());
        return newPackageDetail;
    }

}

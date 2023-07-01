package com.projects.digitalpostmasterrest.dto;

import com.projects.digitalpostmasterrest.common.AuditModel;
import com.projects.digitalpostmasterrest.model.Package;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageDto extends AuditModel {

    private String packageId;
    private String sender;
    private String receiver;
    private String receiverAddress;
    private String dimensions;
    private Float weight;
    private String instructions;

    public Package toEntity() {
        Package newPackage = new Package();
        newPackage.setPackageId(this.getPackageId());
        newPackage.setSender(this.getSender());
        newPackage.setReceiver(this.getReceiver());
        newPackage.setReceiverAddress(this.getReceiverAddress());
        newPackage.setDimensions(this.getDimensions());
        newPackage.setWeight(this.getWeight());
        newPackage.setInstructions(this.getInstructions());
        newPackage.setCreatedDate(this.getCreatedDate());
        newPackage.setCreatedTime(this.getCreatedTime());
        newPackage.setCreatedBy(this.getCreatedBy());
        return newPackage;
    }

}

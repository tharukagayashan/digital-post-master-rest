package com.projects.digitalpostmasterrest.model;

import com.projects.digitalpostmasterrest.common.AuditModel;
import com.projects.digitalpostmasterrest.dto.PackageDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "PACKAGE")
public class Package extends AuditModel {

    @Id
    private String packageId;
    @Field
    private String sender;
    @Field
    private String receiver;
    @Field
    private String receiverAddress;
    @Field
    private String dimensions;
    @Field
    private Float weight;
    @Field
    private String instructions;

    public PackageDto toDto() {
        PackageDto packageDto = new PackageDto();
        packageDto.setPackageId(this.packageId);
        packageDto.setSender(this.getSender());
        packageDto.setReceiver(this.getReceiver());
        packageDto.setReceiverAddress(this.receiverAddress);
        packageDto.setDimensions(this.getDimensions());
        packageDto.setWeight(this.getWeight());
        packageDto.setInstructions(this.getInstructions());
        packageDto.setCreatedDate(this.getCreatedDate());
        packageDto.setCreatedTime(this.getCreatedTime());
        packageDto.setCreatedBy(this.getCreatedBy());
        return packageDto;
    }

}

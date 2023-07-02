package com.projects.digitalpostmasterrest.model;

import com.projects.digitalpostmasterrest.common.AuditModel;
import com.projects.digitalpostmasterrest.dto.PackageDetailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PACKAGE")
public class PackageDetail extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PACKAGE_ID")
    private Integer packageId;
    @Column(name = "SENDER")
    private String sender;
    @Column(name = "RECEIVER")
    private String receiver;
    @Column(name = "RECEIVER_ADDRESS")
    private String receiverAddress;
    @Column(name = "DIMENSIONS")
    private String dimensions;
    @Column(name = "WEIGHT")
    private Float weight;
    @Column(name = "INSTRUCTIONS")
    private String instructions;

    public PackageDetailDto toDto() {
        PackageDetailDto packageDetailDto = new PackageDetailDto();
        packageDetailDto.setPackageId(this.getPackageId());
        packageDetailDto.setSender(this.getSender());
        packageDetailDto.setReceiver(this.getReceiver());
        packageDetailDto.setReceiverAddress(this.getReceiverAddress());
        packageDetailDto.setDimensions(this.getDimensions());
        packageDetailDto.setWeight(this.getWeight());
        packageDetailDto.setInstructions(this.getInstructions());
        packageDetailDto.setCreatedDate(this.getCreatedDate());
        packageDetailDto.setCreatedBy(this.getCreatedBy());
        return packageDetailDto;
    }

}

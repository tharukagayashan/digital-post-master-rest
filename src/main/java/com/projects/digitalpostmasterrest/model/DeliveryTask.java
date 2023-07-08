package com.projects.digitalpostmasterrest.model;

import com.projects.digitalpostmasterrest.common.AuditModel;
import com.projects.digitalpostmasterrest.dto.DeliveryTaskDto;
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
@Table(name = "DELIVERY_TASK")
public class DeliveryTask extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DELIVERY_TASK_ID")
    private Integer deliveryTaskId;
    @Column(name = "PICKUP_ADDRESS")
    private String pickupAddress;
    @Column(name = "DELIVERY_ADDRESS")
    private String deliveryAddress;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "START_TIME")
    private LocalDateTime startTime;
    @Column(name = "END_TIME")
    private LocalDateTime endTime;
    @Column(name = "REF_NO")
    private String referenceNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PACKAGE_ID", referencedColumnName = "PACKAGE_ID", nullable = false)
    private PackageDetail packageDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGENT_ID", referencedColumnName = "AGENT_ID", nullable = false)
    private Agent agent;

    public DeliveryTaskDto toDto() {
        DeliveryTaskDto deliveryTaskDto = new DeliveryTaskDto();
        deliveryTaskDto.setDeliveryTaskId(this.getDeliveryTaskId());
        deliveryTaskDto.setPickupAddress(this.getPickupAddress());
        deliveryTaskDto.setDeliveryAddress(this.getDeliveryAddress());
        deliveryTaskDto.setStatus(this.getStatus());
        deliveryTaskDto.setStartTime(this.getStartTime());
        deliveryTaskDto.setEndTime(this.getEndTime());
        deliveryTaskDto.setPackageId(this.getPackageDetail().getPackageId());
        deliveryTaskDto.setAgentId(this.getAgent().getAgentId());
        deliveryTaskDto.setReferenceNo(this.getReferenceNo());
        return deliveryTaskDto;
    }
}

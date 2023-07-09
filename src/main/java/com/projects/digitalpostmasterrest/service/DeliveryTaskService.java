package com.projects.digitalpostmasterrest.service;

import com.projects.digitalpostmasterrest.dto.DeliveryTaskDto;
import com.projects.digitalpostmasterrest.dto.custom.CreateDeliveryTaskDto;
import com.projects.digitalpostmasterrest.dto.custom.DeliveryTaskStatusUpdateReqDto;
import org.springframework.http.ResponseEntity;

public interface DeliveryTaskService {
    ResponseEntity createDeliveryTask(CreateDeliveryTaskDto createDeliveryTaskDto);

    ResponseEntity getAllDeliveryTasks();

    ResponseEntity getDeliveryTaskById(Integer deliveryTaskId);

    ResponseEntity updateDeliveryTask(DeliveryTaskDto deliveryTaskDto);

    ResponseEntity deleteDeliveryTask(Integer deliveryTaskId);

    ResponseEntity updateDeliveryTaskStatus(DeliveryTaskStatusUpdateReqDto deliveryTaskStatusUpdateReqDto);

    ResponseEntity getDeliveryTaskStatusView();
}

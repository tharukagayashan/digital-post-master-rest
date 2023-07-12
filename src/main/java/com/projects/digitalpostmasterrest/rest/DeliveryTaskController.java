package com.projects.digitalpostmasterrest.rest;

import com.projects.digitalpostmasterrest.dto.DeliveryTaskDto;
import com.projects.digitalpostmasterrest.dto.custom.CreateDeliveryTaskDto;
import com.projects.digitalpostmasterrest.dto.custom.DeliveryTaskStatusUpdateReqDto;
import com.projects.digitalpostmasterrest.dto.custom.DeliveryTaskStatusViewDto;
import com.projects.digitalpostmasterrest.service.DeliveryTaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/delivery-task")
public class DeliveryTaskController {
    private final DeliveryTaskService deliveryTaskService;

    public DeliveryTaskController(DeliveryTaskService deliveryTaskService) {
        this.deliveryTaskService = deliveryTaskService;
    }

    @PostMapping("/create")
    public ResponseEntity<DeliveryTaskDto> createDeliveryTask(@RequestBody CreateDeliveryTaskDto createDeliveryTaskDto) {
        ResponseEntity response = deliveryTaskService.createDeliveryTask(createDeliveryTaskDto);
        return response;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<DeliveryTaskDto>> getAllDeliveryTasks() {
        ResponseEntity response = deliveryTaskService.getAllDeliveryTasks();
        return response;
    }

    @GetMapping("/get/{deliveryTaskId}")
    public ResponseEntity<DeliveryTaskDto> getDeliveryTaskById(@PathVariable Integer deliveryTaskId) {
        ResponseEntity response = deliveryTaskService.getDeliveryTaskById(deliveryTaskId);
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<DeliveryTaskDto> updateDeliveryTask(@RequestBody DeliveryTaskDto deliveryTaskDto) {
        ResponseEntity response = deliveryTaskService.updateDeliveryTask(deliveryTaskDto);
        return response;
    }

    @DeleteMapping("/delete/{deliveryTaskId}")
    public ResponseEntity<Integer> deleteDeliveryTask(@PathVariable Integer deliveryTaskId) {
        ResponseEntity response = deliveryTaskService.deleteDeliveryTask(deliveryTaskId);
        return response;
    }

    @PutMapping("/status-update")
    public ResponseEntity<DeliveryTaskDto> updateDeliveryTaskStatus(@RequestBody DeliveryTaskStatusUpdateReqDto deliveryTaskStatusUpdateReqDto) {
        ResponseEntity response = deliveryTaskService.updateDeliveryTaskStatus(deliveryTaskStatusUpdateReqDto);
        return response;
    }

    @GetMapping("/get/status-view")
    public ResponseEntity<List<DeliveryTaskStatusViewDto>> getDeliveryTaskStatusView() {
        ResponseEntity response = deliveryTaskService.getDeliveryTaskStatusView();
        return response;
    }

    @GetMapping("/search")
    public ResponseEntity<List<DeliveryTaskDto>> searchDeliveryTaskByReferenceNo(
            @RequestParam(defaultValue = "") String referenceNo
    ) {
        ResponseEntity response = deliveryTaskService.searchDeliveryTaskByReferenceNo(referenceNo);
        return response;
    }

}
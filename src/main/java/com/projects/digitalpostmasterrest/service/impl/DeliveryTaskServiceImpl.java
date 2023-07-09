package com.projects.digitalpostmasterrest.service.impl;

import com.projects.digitalpostmasterrest.common.MailService;
import com.projects.digitalpostmasterrest.dao.AgentDao;
import com.projects.digitalpostmasterrest.dao.DeliveryTaskDao;
import com.projects.digitalpostmasterrest.dao.PackageDetailDao;
import com.projects.digitalpostmasterrest.dto.DeliveryTaskDto;
import com.projects.digitalpostmasterrest.dto.custom.CreateDeliveryTaskDto;
import com.projects.digitalpostmasterrest.dto.custom.DeliveryTaskStatusUpdateReqDto;
import com.projects.digitalpostmasterrest.dto.custom.DeliveryTaskStatusViewDto;
import com.projects.digitalpostmasterrest.dto.custom.MailReqDto;
import com.projects.digitalpostmasterrest.enums.StatusEnum;
import com.projects.digitalpostmasterrest.error.ErrorAlert;
import com.projects.digitalpostmasterrest.model.Agent;
import com.projects.digitalpostmasterrest.model.DeliveryTask;
import com.projects.digitalpostmasterrest.model.PackageDetail;
import com.projects.digitalpostmasterrest.service.DeliveryTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.projects.digitalpostmasterrest.constant.Constants.*;

@Slf4j
@Service
public class DeliveryTaskServiceImpl implements DeliveryTaskService {
    private final PackageDetailDao packageDetailDao;
    private final AgentDao agentDao;
    private final DeliveryTaskDao deliveryTaskDao;
    private final MailService mailService;
    private final JavaMailSender mailSender;

    public DeliveryTaskServiceImpl(DeliveryTaskDao deliveryTaskDao,
                                   AgentDao agentDao,
                                   PackageDetailDao packageDetailDao, MailService mailService, JavaMailSender mailSender) {
        this.deliveryTaskDao = deliveryTaskDao;
        this.agentDao = agentDao;
        this.packageDetailDao = packageDetailDao;
        this.mailService = mailService;
        this.mailSender = mailSender;
    }

    @Override
    public ResponseEntity createDeliveryTask(CreateDeliveryTaskDto createDeliveryTaskDto) {
        try {
            Optional<Agent> optAgent = agentDao.findById(createDeliveryTaskDto.getAgentId());
            Optional<PackageDetail> optPackage = packageDetailDao.findById(createDeliveryTaskDto.getPackageId());
            Agent agent = null;
            PackageDetail packageDetail = null;
            if (!optAgent.isPresent()) {
                throw new ErrorAlert(AGENT_NOT_FOUND, "400");
            } else {
                agent = optAgent.get();
            }
            if (!optPackage.isPresent()) {
                throw new ErrorAlert(PACKAGE_NOT_FOUND, "400");
            } else {
                packageDetail = optPackage.get();
            }

            String refNo = UUID.randomUUID().toString();

            DeliveryTask deliveryTask = new DeliveryTask();
            deliveryTask.setPickupAddress(createDeliveryTaskDto.getPickupAddress());
            deliveryTask.setDeliveryAddress(createDeliveryTaskDto.getDeliveryAddress());
            deliveryTask.setStatus(StatusEnum.NEW.name());
            deliveryTask.setStartTime(LocalDateTime.now());
            deliveryTask.setPackageDetail(packageDetail);
            deliveryTask.setAgent(agent);
            deliveryTask.setReferenceNo(refNo);

            deliveryTask = deliveryTaskDao.save(deliveryTask);
            if (deliveryTask == null) {
                log.error(DELIVERY_TASK_CREATE_ERROR);
                throw new ErrorAlert(DELIVERY_TASK_CREATE_ERROR, "400");
            } else {

                MailReqDto userMail = new MailReqDto();
                userMail.setTo(deliveryTask.getPackageDetail().getUserDetail().getEmail());
                userMail.setSubject(PACKAGE_DELIVERY_TASK_CREATED_MAIL_SUBJECT);
                userMail.setBody("Your package reference no is : " + deliveryTask.getReferenceNo());
                mailService.sendMail(mailSender, userMail);
                log.info("User mail : {}", userMail.getTo());
                log.info("User mail send...");

                MailReqDto agentMail = new MailReqDto();
                agentMail.setTo(agent.getUserDetail().getEmail());
                agentMail.setSubject(PACKAGE_DELIVERY_TASK_CREATED_MAIL_SUBJECT);
                agentMail.setBody("You have been assigned a new package delivery activity : " + deliveryTask.getReferenceNo());
                mailService.sendMail(mailSender, agentMail);
                log.info("Agent mail : {}", agentMail.getTo());
                log.info("Agent mail send...");
                return ResponseEntity.ok(deliveryTask.toDto());
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity getAllDeliveryTasks() {
        try {
            List<DeliveryTask> deliveryTaskList = deliveryTaskDao.findAll();
            if (deliveryTaskList.isEmpty()) {
                throw new ErrorAlert(DELIVERY_TASK_LIST_EMPTY, "400");
            } else {
                List<DeliveryTaskDto> deliveryTaskDtoList = new ArrayList<>();
                for (DeliveryTask d : deliveryTaskList) {
                    deliveryTaskDtoList.add(d.toDto());
                }
                return ResponseEntity.ok(deliveryTaskDtoList);
            }
        } catch (Exception e) {
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity getDeliveryTaskById(Integer deliveryTaskId) {
        try {
            if (deliveryTaskId == null) {
                throw new ErrorAlert(DELIVERY_TASK_ID_NULL, "400");
            } else {
                Optional<DeliveryTask> optDeliveryTask = deliveryTaskDao.findById(deliveryTaskId);
                if (!optDeliveryTask.isPresent()) {
                    log.error(DELIVERY_TASK_NOT_FOUND);
                    throw new ErrorAlert(DELIVERY_TASK_NOT_FOUND, "400");
                } else {
                    DeliveryTask deliveryTask = optDeliveryTask.get();
                    return ResponseEntity.ok(deliveryTask.toDto());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity updateDeliveryTask(DeliveryTaskDto deliveryTaskDto) {
        try {
            if (deliveryTaskDto.getDeliveryTaskId() == null) {
                throw new ErrorAlert(DELIVERY_TASK_ID_NULL, "400");
            } else {
                PackageDetail packageDetail = null;
                Agent agent = null;
                if (deliveryTaskDto.getPackageId() == null) {
                    throw new ErrorAlert(PACKAGE_ID_NULL, "400");
                } else {
                    Optional<PackageDetail> optPackage = packageDetailDao.findById(deliveryTaskDto.getPackageId());
                    if (!optPackage.isPresent()) {
                        throw new ErrorAlert(PACKAGE_NOT_FOUND, "400");
                    } else {
                        packageDetail = optPackage.get();
                    }
                }
                if (deliveryTaskDto.getAgentId() == null) {
                    throw new ErrorAlert(AGENT_ID_NULL, "400");
                } else {
                    Optional<Agent> optAgent = agentDao.findById(deliveryTaskDto.getAgentId());
                    if (!optAgent.isPresent()) {
                        throw new ErrorAlert(AGENT_NOT_FOUND, "400");
                    } else {
                        agent = optAgent.get();
                    }
                }
                Optional<DeliveryTask> optDeliveryTask = deliveryTaskDao.findById(deliveryTaskDto.getDeliveryTaskId());
                if (!optDeliveryTask.isPresent()) {
                    log.error(DELIVERY_TASK_NOT_FOUND);
                    throw new ErrorAlert(DELIVERY_TASK_NOT_FOUND, "400");
                } else {
                    DeliveryTask deliveryTask = optDeliveryTask.get();
                    deliveryTask.setPickupAddress(deliveryTaskDto.getPickupAddress());
                    deliveryTask.setDeliveryAddress(deliveryTaskDto.getDeliveryAddress());
                    deliveryTask.setStatus(deliveryTaskDto.getStatus());
                    deliveryTask.setStartTime(deliveryTaskDto.getStartTime());
                    deliveryTask.setEndTime(deliveryTaskDto.getEndTime());
                    deliveryTask.setPackageDetail(packageDetail);
                    deliveryTask.setAgent(agent);
                    deliveryTask = deliveryTaskDao.save(deliveryTask);
                    log.info(DELIVERY_TASK_UPDATED, "400");
                    return ResponseEntity.ok(deliveryTask.toDto());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity deleteDeliveryTask(Integer deliveryTaskId) {
        try {
            if (deliveryTaskId == null) {
                throw new ErrorAlert(DELIVERY_TASK_ID_NULL, "400");
            } else {
                Optional<DeliveryTask> optDeliveryTask = deliveryTaskDao.findById(deliveryTaskId);
                if (!optDeliveryTask.isPresent()) {
                    log.error(DELIVERY_TASK_NOT_FOUND);
                    throw new ErrorAlert(DELIVERY_TASK_NOT_FOUND, "400");
                } else {
                    deliveryTaskDao.deleteById(deliveryTaskId);
                    Optional<DeliveryTask> check = deliveryTaskDao.findById(deliveryTaskId);
                    if (check.isPresent()) {
                        throw new ErrorAlert(DELIVERY_TASK_DELETE_ERROR, "400");
                    } else {
                        return ResponseEntity.ok(deliveryTaskId);
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity updateDeliveryTaskStatus(DeliveryTaskStatusUpdateReqDto deliveryTaskStatusUpdateReqDto) {
        try {
            DeliveryTask deliveryTask = null;
            String status = null;
            if (deliveryTaskStatusUpdateReqDto.getDeliveryTaskId() == null) {
                throw new ErrorAlert(DELIVERY_TASK_ID_NULL, "400");
            } else {
                Optional<DeliveryTask> optDeliveryTask = deliveryTaskDao.findById(deliveryTaskStatusUpdateReqDto.getDeliveryTaskId());
                if (!optDeliveryTask.isPresent()) {
                    log.error(DELIVERY_TASK_NOT_FOUND);
                    throw new ErrorAlert(DELIVERY_TASK_NOT_FOUND, "400");
                } else {
                    deliveryTask = optDeliveryTask.get();
                    switch (deliveryTaskStatusUpdateReqDto.getStatus()) {
                        case "pickup":
                            status = StatusEnum.PICKUPED.name();
                            break;
                        case "shipped":
                            status = StatusEnum.SHIPPED.name();
                            break;
                        case "delivered":
                            status = StatusEnum.DELIVERED.name();
                            break;
                        case "completed":
                            status = StatusEnum.COMPLETED.name();
                            deliveryTask.setEndTime(LocalDateTime.now());
                            break;
                        default:
                            status = StatusEnum.PENDING.name();
                            break;
                    }
                    deliveryTask.setStatus(status);
                    deliveryTask = deliveryTaskDao.save(deliveryTask);
                    log.info(DELIVERY_TASK_STATUS_UPDATED, "400");
                    return ResponseEntity.ok(deliveryTask.toDto());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity getDeliveryTaskStatusView() {
        try {
            List<DeliveryTask> deliveryTaskList = deliveryTaskDao.findAll();
            if (deliveryTaskList.isEmpty()) {
                throw new ErrorAlert(DELIVERY_TASK_NOT_FOUND, "400");
            } else {
                List<DeliveryTaskStatusViewDto> statusViewList = new ArrayList<>();
                StatusEnum[] statusList = StatusEnum.values();
                for (StatusEnum status : statusList) {
                    Integer count = 0;
                    DeliveryTaskStatusViewDto statusViewDto = new DeliveryTaskStatusViewDto();
                    List<DeliveryTaskDto> deliveryTasks = new ArrayList<>();
                    for (DeliveryTask d : deliveryTaskList) {
                        if (status.name().equalsIgnoreCase(d.getStatus())) {
                            count = count + 1;
                            deliveryTasks.add(d.toDto());
                        }
                    }
                    statusViewDto.setTaskList(deliveryTasks);
                    statusViewDto.setCount(count);
                    statusViewDto.setStatus(status.name());
                    statusViewList.add(statusViewDto);
                }
                return ResponseEntity.ok(statusViewList);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

}
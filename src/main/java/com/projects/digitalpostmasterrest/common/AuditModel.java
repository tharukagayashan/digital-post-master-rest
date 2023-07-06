package com.projects.digitalpostmasterrest.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners({AuditingEntityListener.class})
public class AuditModel {

    @CreatedDate
    @Column(name = "CREATED_DATE_TIME", updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();
    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false)
    private String createdBy = "Admin User";

}

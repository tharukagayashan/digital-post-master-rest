package com.projects.digitalpostmasterrest.dto;

import com.projects.digitalpostmasterrest.common.AuditModel;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends AuditModel {

    private String userId;
    private String name;
    private String email;
    private String password;
    private String address;
    private String contactNo;

}
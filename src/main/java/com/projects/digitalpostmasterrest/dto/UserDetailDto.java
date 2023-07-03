package com.projects.digitalpostmasterrest.dto;

import com.projects.digitalpostmasterrest.common.AuditModel;
import com.projects.digitalpostmasterrest.model.UserDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDto extends AuditModel {

    private Integer userId;
    private String name;
    private String username;
    private String email;
    private String password;
    private String address;
    private String contactNo;
    private String userType;

    public UserDetail toEntity() {
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(this.getUserId());
        userDetail.setName(this.getName());
        userDetail.setEmail(this.getEmail());
        userDetail.setPassword(this.getPassword());
        userDetail.setAddress(this.getAddress());
        userDetail.setContactNo(this.getContactNo());
        userDetail.setCreatedDate(this.getCreatedDate());
        userDetail.setCreatedBy(this.getCreatedBy());
        userDetail.setUsername(this.getUsername());
        userDetail.setUserType(this.getUserType());
        return userDetail;
    }

}
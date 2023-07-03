package com.projects.digitalpostmasterrest.model;

import com.projects.digitalpostmasterrest.common.AuditModel;
import com.projects.digitalpostmasterrest.dto.UserDetailDto;
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
@Table(name = "USER_DETAILS")
public class UserDetail extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "NAME")
    private String name;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "CONTACT_NO")
    private String contactNo;

    @Column(name = "USER_TYPE")
    private String userType;

    public UserDetailDto toDto() {
        UserDetailDto userDetailDto = new UserDetailDto();
        userDetailDto.setUserId(this.getUserId());
        userDetailDto.setName(this.getName());
        userDetailDto.setEmail(this.getEmail());
        userDetailDto.setPassword(this.getPassword());
        userDetailDto.setAddress(this.getAddress());
        userDetailDto.setContactNo(this.getContactNo());
        userDetailDto.setCreatedDate(this.getCreatedDate());
        userDetailDto.setCreatedBy(this.getCreatedBy());
        userDetailDto.setUsername(this.getUsername());
        userDetailDto.setUserType(this.getUserType());
        return userDetailDto;
    }
}
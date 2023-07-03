package com.projects.digitalpostmasterrest.service;

import com.projects.digitalpostmasterrest.dto.UserDetailDto;
import com.projects.digitalpostmasterrest.dto.custom.LoginReqDto;
import com.projects.digitalpostmasterrest.dto.custom.UserCreateReqDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity createUser(UserCreateReqDto userCreateReqDto);
    ResponseEntity getAllUsers();
    ResponseEntity loginUser(LoginReqDto loginReqDto);
    ResponseEntity getUserById(Integer userId);
    ResponseEntity getUserByUsername(String username);
    ResponseEntity deleteUser(Integer userId);

    ResponseEntity updateUser(UserDetailDto userDetailDto);
}

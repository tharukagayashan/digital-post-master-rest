package com.projects.digitalpostmasterrest.rest;

import com.projects.digitalpostmasterrest.dto.UserDetailDto;
import com.projects.digitalpostmasterrest.dto.custom.UserCreateReqDto;
import com.projects.digitalpostmasterrest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDetailDto> createUser(@Valid @RequestBody UserCreateReqDto userCreateReqDto) {
        ResponseEntity response = userService.createUser(userCreateReqDto);
        return response;
    }

}

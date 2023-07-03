package com.projects.digitalpostmasterrest.rest;

import com.projects.digitalpostmasterrest.dto.UserDetailDto;
import com.projects.digitalpostmasterrest.dto.custom.LoginReqDto;
import com.projects.digitalpostmasterrest.dto.custom.LoginResDto;
import com.projects.digitalpostmasterrest.dto.custom.UserCreateReqDto;
import com.projects.digitalpostmasterrest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDetailDto> createUser(@Valid @RequestBody UserCreateReqDto userCreateReqDto) {
        ResponseEntity response = userService.createUser(userCreateReqDto);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResDto> loginUser(@Valid @RequestBody LoginReqDto loginReqDto) {
        ResponseEntity response = userService.loginUser(loginReqDto);
        return response;
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<UserDetailDto>> getAllUsers() {
        ResponseEntity response = userService.getAllUsers();
        return response;
    }

}

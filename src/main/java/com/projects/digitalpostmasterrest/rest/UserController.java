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

@CrossOrigin
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

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDetailDto> getUserById(@PathVariable Integer userId) {
        ResponseEntity response = userService.getUserById(userId);
        return response;
    }

    @GetMapping("/get/username/{username}")
    public ResponseEntity<UserDetailDto> getUserByUsername(@PathVariable String username) {
        ResponseEntity response = userService.getUserByUsername(username);
        return response;
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Integer> deleteUser(@PathVariable Integer userId) {
        ResponseEntity response = userService.deleteUser(userId);
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<UserDetailDto> updateUser(@RequestBody UserDetailDto userDetailDto) {
        ResponseEntity response = userService.updateUser(userDetailDto);
        return response;
    }

}

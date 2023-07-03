package com.projects.digitalpostmasterrest.service.impl;

import com.projects.digitalpostmasterrest.dao.UserDetailDao;
import com.projects.digitalpostmasterrest.dto.custom.UserCreateReqDto;
import com.projects.digitalpostmasterrest.error.ErrorAlert;
import com.projects.digitalpostmasterrest.model.UserDetail;
import com.projects.digitalpostmasterrest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.projects.digitalpostmasterrest.constant.Contants.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserDetailDao userDetailDao;

    public UserServiceImpl(UserDetailDao userDetailDao) {
        this.userDetailDao = userDetailDao;
    }

    @Override
    public ResponseEntity createUser(UserCreateReqDto userCreateReqDto) {
        try {

            String contactNo = userCreateReqDto.getContactNo();
            String email = userCreateReqDto.getEmail();
            if (contactNo.isEmpty()) {
                throw new ErrorAlert(CONTACT_NO_EMPTY, "400");
            }
            if (email.isEmpty()) {
                throw new ErrorAlert(EMAIL_ADDRESS_EMPTY, "400");
            }

            UserDetail userDetail = new UserDetail();
            userDetail.setName(userCreateReqDto.getName());
            userDetail.setEmail(email);
            userDetail.setPassword(userCreateReqDto.getPassword());
            userDetail.setAddress(userCreateReqDto.getAddress());
            userDetail.setContactNo(contactNo);

            userDetail = userDetailDao.save(userDetail);
            if (userDetail == null) {
                throw new ErrorAlert(USER_CREATE_ERROR, "400");
            } else {
                return ResponseEntity.ok(userDetail.toDto());
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }
}

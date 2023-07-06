package com.projects.digitalpostmasterrest.service.impl;

import com.projects.digitalpostmasterrest.config.JwtUtils;
import com.projects.digitalpostmasterrest.config.PasswordUtils;
import com.projects.digitalpostmasterrest.dao.UserDetailDao;
import com.projects.digitalpostmasterrest.dto.UserDetailDto;
import com.projects.digitalpostmasterrest.dto.custom.LoginReqDto;
import com.projects.digitalpostmasterrest.dto.custom.LoginResDto;
import com.projects.digitalpostmasterrest.dto.custom.UserCreateReqDto;
import com.projects.digitalpostmasterrest.enums.UserTypeEnum;
import com.projects.digitalpostmasterrest.error.ErrorAlert;
import com.projects.digitalpostmasterrest.model.UserDetail;
import com.projects.digitalpostmasterrest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.projects.digitalpostmasterrest.constant.Constants.*;

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
            String username = userCreateReqDto.getUsername();
            if (username.isEmpty()) {
                throw new ErrorAlert(USERNAME_EMPTY, "400");
            }
            if (contactNo.isEmpty()) {
                throw new ErrorAlert(CONTACT_NO_EMPTY, "400");
            }
            if (email.isEmpty()) {
                throw new ErrorAlert(EMAIL_ADDRESS_EMPTY, "400");
            }

            Optional<UserDetail> optUser = userDetailDao.findByUsernameOrEmail(userCreateReqDto.getUsername(), userCreateReqDto.getEmail());
            if (optUser.isPresent()) {
                throw new ErrorAlert(USER_ALREADY_EXIST, "400");
            }

            UserDetail userDetail = new UserDetail();
            userDetail.setName(userCreateReqDto.getName());
            userDetail.setEmail(email);
            userDetail.setUsername(username);
            userDetail.setPassword(userCreateReqDto.getPassword());
            userDetail.setAddress(userCreateReqDto.getAddress());
            userDetail.setContactNo(contactNo);
            userDetail.setUserType(UserTypeEnum.USER.name());

            PasswordUtils passwordUtils = new PasswordUtils();
            String encodedPassword = passwordUtils.encodePassword(userDetail.getPassword());

            System.out.println("Password         : " + userDetail.getPassword());
            System.out.println("Encoded Password : " + encodedPassword);

            userDetail.setPassword(encodedPassword);

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

    @Override
    public ResponseEntity getAllUsers() {
        try {
            List<UserDetail> userDetailList = userDetailDao.findAll();
            if (userDetailList.isEmpty()) {
                throw new ErrorAlert(USER_LIST_EMPTY, "400");
            } else {
                List<UserDetailDto> userDetailDtoList = new ArrayList<>();
                for (UserDetail d : userDetailList) {
                    userDetailDtoList.add(d.toDto());
                }
                return ResponseEntity.ok(userDetailDtoList);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity loginUser(LoginReqDto loginReqDto) {
        try {
            Optional<UserDetail> optUser = userDetailDao.findByUsername(loginReqDto.getUsername());
            if (!optUser.isPresent()) {
                throw new ErrorAlert(LOGIN_ERROR, "400");
            } else {
                LoginResDto loginRes = new LoginResDto();
                UserDetail user = optUser.get();
                String encodedPassword = user.getPassword();
                PasswordUtils passwordUtils = new PasswordUtils();
                Boolean flag = passwordUtils.isPasswordValid(loginReqDto.getPassword(), encodedPassword);
                if (flag) {
                    JwtUtils jwtUtils = new JwtUtils();
                    String token = jwtUtils.generateToken(user.getUsername());

                    loginRes.setIsValid(true);
                    loginRes.setToken(token);
                    return ResponseEntity.ok(loginRes);
                } else {
                    throw new ErrorAlert(LOGIN_ERROR, "400");
                }
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity getUserById(Integer userId) {
        try {
            if (userId == null) {
                throw new ErrorAlert(USER_ID_NULL, "400");
            } else {
                Optional<UserDetail> optUser = userDetailDao.findById(userId);
                if (!optUser.isPresent()) {
                    throw new ErrorAlert(USER_NOT_FOUND, "400");
                } else {
                    return ResponseEntity.ok(optUser.get().toDto());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity getUserByUsername(String username) {
        try {
            if (username.isEmpty()) {
                throw new ErrorAlert(USERNAME_EMPTY, "400");
            } else {
                Optional<UserDetail> optUser = userDetailDao.findByUsername(username);
                if (!optUser.isPresent()) {
                    throw new ErrorAlert(USER_NOT_FOUND, "400");
                } else {
                    return ResponseEntity.ok(optUser.get().toDto());
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity deleteUser(Integer userId) {
        try {
            if (userId == null) {
                throw new ErrorAlert(USER_ID_NULL, "400");
            } else {
                Optional<UserDetail> optUser = userDetailDao.findById(userId);
                if (!optUser.isPresent()) {
                    throw new ErrorAlert(USER_NOT_FOUND, "400");
                } else {
                    userDetailDao.deleteById(userId);
                    Optional<UserDetail> check = userDetailDao.findById(userId);
                    if (!check.isPresent()) {
                        return ResponseEntity.ok(userId);
                    } else {
                        throw new ErrorAlert(USER_DELETE_ERROR, "400");
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }

    @Override
    public ResponseEntity updateUser(UserDetailDto userDetailDto) {
        try {
            if (userDetailDto.getUserId() == null) {
                log.error(USER_ID_NULL);
                throw new ErrorAlert(USER_ID_NULL, "400");
            } else {
                Optional<UserDetail> optUser = userDetailDao.findById(userDetailDto.getUserId());
                if (!optUser.isPresent()) {
                    throw new ErrorAlert(USER_NOT_FOUND, "400");
                } else {
                    UserDetail userDetail = optUser.get();
                    userDetail.setName(userDetailDto.getName());
                    userDetail.setUsername(userDetailDto.getUsername());
                    userDetail.setEmail(userDetailDto.getEmail());
                    userDetail.setPassword(userDetailDto.getPassword());
                    userDetail.setAddress(userDetailDto.getAddress());
                    userDetail.setContactNo(userDetailDto.getContactNo());

                    userDetail = userDetailDao.save(userDetail);
                    return ResponseEntity.ok(userDetail.toDto());
                }
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new ErrorAlert(e.getMessage(), "400");
        }
    }
}

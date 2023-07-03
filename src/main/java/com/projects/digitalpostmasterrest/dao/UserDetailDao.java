package com.projects.digitalpostmasterrest.dao;

import com.projects.digitalpostmasterrest.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailDao extends JpaRepository<UserDetail, Integer> {
    Optional<UserDetail> findByUsernameOrEmail(String username, String email);

    Optional<UserDetail> findByUsername(String username);
}

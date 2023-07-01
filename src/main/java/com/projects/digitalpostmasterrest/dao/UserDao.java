package com.projects.digitalpostmasterrest.dao;

import com.projects.digitalpostmasterrest.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends MongoRepository<User, String> {
}

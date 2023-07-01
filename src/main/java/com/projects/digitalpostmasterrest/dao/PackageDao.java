package com.projects.digitalpostmasterrest.dao;

import com.projects.digitalpostmasterrest.model.Package;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageDao extends MongoRepository<Package,String> {
}

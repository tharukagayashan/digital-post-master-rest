package com.projects.digitalpostmasterrest.dao;

import com.projects.digitalpostmasterrest.model.PackageDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageDetailDao extends JpaRepository<PackageDetail,Integer> {
}

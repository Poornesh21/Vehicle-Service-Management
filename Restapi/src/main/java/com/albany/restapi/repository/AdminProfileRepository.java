// Restapi/src/main/java/com/albany/restapi/repository/AdminProfileRepository.java
package com.albany.restapi.repository;

import com.albany.restapi.model.AdminProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminProfileRepository extends JpaRepository<AdminProfile, Integer> {
    AdminProfile findByUserId(Integer userId);
}
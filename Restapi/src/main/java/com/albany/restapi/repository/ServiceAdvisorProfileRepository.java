// Restapi/src/main/java/com/albany/restapi/repository/ServiceAdvisorProfileRepository.java
package com.albany.restapi.repository;

import com.albany.restapi.model.ServiceAdvisorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceAdvisorProfileRepository extends JpaRepository<ServiceAdvisorProfile, Integer> {
    ServiceAdvisorProfile findByUserId(Integer userId);
    
    // Find service advisors by department
    List<ServiceAdvisorProfile> findByDepartment(String department);
    
    // Find service advisors by specialization
    List<ServiceAdvisorProfile> findBySpecialization(String specialization);
}
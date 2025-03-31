// Restapi/src/main/java/com/albany/restapi/repository/ServiceAdvisorProfileRepository.java
package com.albany.restapi.repository;

import com.albany.restapi.model.ServiceAdvisorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ServiceAdvisorProfileRepository extends JpaRepository<ServiceAdvisorProfile, Integer> {
    Optional<ServiceAdvisorProfile> findByUserId(Integer userId);
    
    List<ServiceAdvisorProfile> findByDepartment(String department);
    
    List<ServiceAdvisorProfile> findBySpecialization(String specialization);
    
    @Query("SELECT sp FROM ServiceAdvisorProfile sp JOIN sp.user u WHERE " +
           "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(sp.department) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(sp.specialization) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<ServiceAdvisorProfile> searchAdvisors(@Param("searchTerm") String searchTerm);
    
    @Query("SELECT COUNT(sr) FROM ServiceRequest sr WHERE sr.serviceAdvisorId = :advisorId AND " +
           "sr.status IN ('Received', 'Diagnosis', 'Repair')")
    Integer countActiveServicesByAdvisorId(@Param("advisorId") Integer advisorId);
}
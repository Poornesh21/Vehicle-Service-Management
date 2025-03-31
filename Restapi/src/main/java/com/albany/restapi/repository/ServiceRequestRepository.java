// Restapi/src/main/java/com/albany/restapi/repository/ServiceRequestRepository.java
package com.albany.restapi.repository;

import com.albany.restapi.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Integer> {
    List<ServiceRequest> findByStatus(ServiceRequest.Status status);

    List<ServiceRequest> findByStatusOrderByDeliveryDateAsc(ServiceRequest.Status status);

    List<ServiceRequest> findByStatusInOrderByDeliveryDateAsc(List<ServiceRequest.Status> statuses);

    List<ServiceRequest> findByStatusAndDeliveryDateAfter(ServiceRequest.Status status, LocalDate date);

    long countByStatus(ServiceRequest.Status status);

    long countByStatusIn(List<ServiceRequest.Status> statuses);

    @Query("SELECT COUNT(sr) FROM ServiceRequest sr WHERE sr.status = :status AND sr.deliveryDate BETWEEN :startDate AND :endDate")
    long countCompletedByDateRange(@Param("status") ServiceRequest.Status status, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
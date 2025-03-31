// Restapi/src/main/java/com/albany/restapi/model/ServiceRequest.java
package com.albany.restapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ServiceRequests")
@Data
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer requestId;
    
    @Column(name = "vehicle_id")
    private Integer vehicleId;
    
    @Column(name = "service_type")
    private String serviceType;
    
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
    
    @Column(name = "additional_description")
    private String additionalDescription;
    
    @Column(name = "admin_id")
    private Integer adminId;
    
    @Column(name = "service_advisor_id")
    private Integer serviceAdvisorId;
    
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @ManyToOne
    @JoinColumn(name = "vehicle_id", insertable = false, updatable = false)
    private Vehicle vehicle;
    
    @ManyToOne
    @JoinColumn(name = "admin_id", insertable = false, updatable = false)
    private AdminProfile admin;
    
    @ManyToOne
    @JoinColumn(name = "service_advisor_id", insertable = false, updatable = false)
    private ServiceAdvisorProfile serviceAdvisor;
    
    public enum Status {
        Received, Diagnosis, Repair, Completed
    }
}
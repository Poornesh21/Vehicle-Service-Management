// Restapi/src/main/java/com/albany/restapi/model/ServiceAdvisorProfile.java
package com.albany.restapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "ServiceAdvisorProfiles")
@Data
public class ServiceAdvisorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advisor_id")
    private Integer advisorId;
    
    @Column(name = "user_id")
    private Integer userId;
    
    private String department;
    
    @Column(name = "hire_date")
    private LocalDate hireDate;
    
    private String specialization;
    
    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
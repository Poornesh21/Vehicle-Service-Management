// Restapi/src/main/java/com/albany/restapi/dto/ServiceAdvisorDTO.java
package com.albany.restapi.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ServiceAdvisorDTO {
    private Integer advisorId;
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password; // Only used for creation, not returned in responses
    private String department;
    private String specialization;
    private LocalDate hireDate;
    private String profileImagePath;
    private Boolean isActive;
    private Integer activeServices; // Count of vehicles currently being serviced
    private Integer workloadPercentage; // Calculated workload percentage
}
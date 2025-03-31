// Restapi/src/main/java/com/albany/restapi/dto/VehicleInServiceDTO.java
package com.albany.restapi.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class VehicleInServiceDTO {
    private Integer vehicleId;
    private String vehicleName;
    private String registrationNumber;
    private String category;
    private String serviceAdvisorName;
    private String serviceAdvisorId;
    private String status;
    private LocalDate startDate;
    private LocalDate estimatedCompletionDate;
}
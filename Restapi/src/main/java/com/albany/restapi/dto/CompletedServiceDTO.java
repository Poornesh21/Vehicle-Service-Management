// Restapi/src/main/java/com/albany/restapi/dto/CompletedServiceDTO.java
package com.albany.restapi.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CompletedServiceDTO {
    private Integer serviceId;
    private Integer vehicleId;
    private String vehicleName;
    private String registrationNumber;
    private String customerName;
    private String serviceAdvisorName;
    private LocalDate completedDate;
    private BigDecimal totalCost;
    private Boolean hasInvoice;
}
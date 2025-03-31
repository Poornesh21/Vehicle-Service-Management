// Restapi/src/main/java/com/albany/restapi/dto/VehicleDueDTO.java
package com.albany.restapi.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class VehicleDueDTO {
    private Integer vehicleId;
    private String vehicleName;
    private String registrationNumber;
    private String category;
    private String customerName;
    private String customerEmail;
    private String status;
    private LocalDate dueDate;
}
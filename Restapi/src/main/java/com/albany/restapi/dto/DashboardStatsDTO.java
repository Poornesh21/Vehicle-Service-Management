// Restapi/src/main/java/com/albany/restapi/dto/DashboardStatsDTO.java
package com.albany.restapi.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class DashboardStatsDTO {
    private Integer vehiclesDue;
    private Integer vehiclesInProgress;
    private Integer vehiclesCompleted;
    private BigDecimal totalRevenue;
    
    private List<VehicleDueDTO> vehiclesDueList;
    private List<VehicleInServiceDTO> vehiclesInServiceList;
    private List<CompletedServiceDTO> completedServicesList;
}
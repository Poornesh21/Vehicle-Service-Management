// Restapi/src/main/java/com/albany/restapi/controller/DashboardController.java
package com.albany.restapi.controller;

import com.albany.restapi.dto.DashboardStatsDTO;
import com.albany.restapi.dto.VehicleDueDTO;
import com.albany.restapi.dto.VehicleInServiceDTO;
import com.albany.restapi.dto.CompletedServiceDTO;
import com.albany.restapi.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats() {
        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }

    @GetMapping("/vehicles-due")
    public ResponseEntity<List<VehicleDueDTO>> getVehiclesDue() {
        return ResponseEntity.ok(dashboardService.getVehiclesDue());
    }

    @GetMapping("/vehicles-in-service")
    public ResponseEntity<List<VehicleInServiceDTO>> getVehiclesInService() {
        return ResponseEntity.ok(dashboardService.getVehiclesInService());
    }

    @GetMapping("/completed-services")
    public ResponseEntity<List<CompletedServiceDTO>> getCompletedServices() {
        return ResponseEntity.ok(dashboardService.getCompletedServices());
    }
    
    @GetMapping("/service-trends")
    public ResponseEntity<Object> getServiceTrends(@RequestParam(defaultValue = "month") String period) {
        return ResponseEntity.ok(dashboardService.getServiceTrends(period));
    }
    
    @GetMapping("/service-distribution")
    public ResponseEntity<Object> getServiceDistribution(@RequestParam(defaultValue = "month") String period) {
        return ResponseEntity.ok(dashboardService.getServiceDistribution(period));
    }
}
// Restapi/src/main/java/com/albany/restapi/service/DashboardService.java
package com.albany.restapi.service;

import com.albany.restapi.dto.CompletedServiceDTO;
import com.albany.restapi.dto.DashboardStatsDTO;
import com.albany.restapi.dto.VehicleDueDTO;
import com.albany.restapi.dto.VehicleInServiceDTO;
import com.albany.restapi.model.ServiceRequest;
import com.albany.restapi.model.Vehicle;
import com.albany.restapi.repository.CustomerProfileRepository;
import com.albany.restapi.repository.ServiceRequestRepository;
import com.albany.restapi.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final ServiceRequestRepository serviceRequestRepository;
    private final VehicleRepository vehicleRepository;
    private final CustomerProfileRepository customerProfileRepository;

    public DashboardService(ServiceRequestRepository serviceRequestRepository,
                           VehicleRepository vehicleRepository,
                           CustomerProfileRepository customerProfileRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
        this.vehicleRepository = vehicleRepository;
        this.customerProfileRepository = customerProfileRepository;
    }

    public DashboardStatsDTO getDashboardStats() {
        DashboardStatsDTO stats = new DashboardStatsDTO();
        
        // For a real implementation, these would come from database queries
        stats.setVehiclesDue(12); // Count of vehicles with status "Received"
        stats.setVehiclesInProgress(8); // Count of vehicles with status "Diagnosis" or "Repair"
        stats.setVehiclesCompleted(5); // Count of vehicles with status "Completed" in last X days
        stats.setTotalRevenue(new BigDecimal("384000")); // Sum of all completed services revenue
        
        // Get the detailed lists
        stats.setVehiclesDueList(getVehiclesDue());
        stats.setVehiclesInServiceList(getVehiclesInService());
        stats.setCompletedServicesList(getCompletedServices());
        
        return stats;
    }

    public List<VehicleDueDTO> getVehiclesDue() {
        // In a real implementation, this would query the database
        // For now, return sample data matching the dashboard UI
        
        List<VehicleDueDTO> vehiclesDue = new ArrayList<>();
        
        // Sample data - in a real implementation, these would be fetched from the database
        VehicleDueDTO v1 = new VehicleDueDTO();
        v1.setVehicleId(1);
        v1.setVehicleName("Royal Enfield Classic");
        v1.setRegistrationNumber("DL01AB1234");
        v1.setCategory("Bike");
        v1.setCustomerName("Rahul Sharma");
        v1.setCustomerEmail("rahul@example.com");
        v1.setStatus("Pending");
        v1.setDueDate(LocalDate.now().plusDays(2));
        vehiclesDue.add(v1);
        
        VehicleDueDTO v2 = new VehicleDueDTO();
        v2.setVehicleId(2);
        v2.setVehicleName("Mahindra Thar");
        v2.setRegistrationNumber("MH02CD5678");
        v2.setCategory("Car");
        v2.setCustomerName("Priya Singh");
        v2.setCustomerEmail("priya@example.com");
        v2.setStatus("Pending");
        v2.setDueDate(LocalDate.now().plusDays(3));
        vehiclesDue.add(v2);
        
        // Add more sample vehicles to match the UI
        VehicleDueDTO v3 = new VehicleDueDTO();
        v3.setVehicleId(3);
        v3.setVehicleName("Tata Prima");
        v3.setRegistrationNumber("KA03EF9012");
        v3.setCategory("Truck");
        v3.setCustomerName("Arjun Patel");
        v3.setCustomerEmail("arjun@example.com");
        v3.setStatus("Pending");
        v3.setDueDate(LocalDate.now().plusDays(1));
        vehiclesDue.add(v3);
        
        // Add more sample data to match the dashboard
        
        return vehiclesDue;
    }

    public List<VehicleInServiceDTO> getVehiclesInService() {
        // In a real implementation, this would query the database
        // For now, return sample data matching the dashboard UI
        
        List<VehicleInServiceDTO> vehiclesInService = new ArrayList<>();
        
        // Sample data - in a real implementation, these would be fetched from the database
        VehicleInServiceDTO v1 = new VehicleInServiceDTO();
        v1.setVehicleId(4);
        v1.setVehicleName("Mahindra Bolero");
        v1.setRegistrationNumber("TN04GH3456");
        v1.setCategory("Car");
        v1.setServiceAdvisorName("Vikram Mehta");
        v1.setServiceAdvisorId("SA-1023");
        v1.setStatus("In Progress");
        v1.setStartDate(LocalDate.now().minusDays(2));
        v1.setEstimatedCompletionDate(LocalDate.now().plusDays(1));
        vehiclesInService.add(v1);
        
        // Add more sample vehicles to match the UI
        
        return vehiclesInService;
    }

    public List<CompletedServiceDTO> getCompletedServices() {
        // In a real implementation, this would query the database
        // For now, return sample data matching the dashboard UI
        
        List<CompletedServiceDTO> completedServices = new ArrayList<>();
        
        // Sample data - in a real implementation, these would be fetched from the database
        CompletedServiceDTO s1 = new CompletedServiceDTO();
        s1.setServiceId(1);
        s1.setVehicleId(7);
        s1.setVehicleName("Maruti Ertiga");
        s1.setRegistrationNumber("DL07KL1234");
        s1.setCustomerName("Amit Verma");
        s1.setServiceAdvisorName("Deepak Sharma");
        s1.setCompletedDate(LocalDate.now());
        s1.setTotalCost(new BigDecimal("42550"));
        s1.setHasInvoice(false);
        completedServices.add(s1);
        
        // Add more sample services to match the UI
        
        return completedServices;
    }
    
    public Map<String, Object> getServiceTrends(String period) {
        Map<String, Object> result = new HashMap<>();
        List<String> labels;
        List<Integer> vehiclesDueData;
        List<Integer> completedServicesData;
        
        if ("week".equals(period)) {
            labels = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
            vehiclesDueData = Arrays.asList(2, 3, 4, 3, 5, 2, 1);
            completedServicesData = Arrays.asList(1, 2, 3, 2, 4, 1, 0);
        } else if ("month".equals(period)) {
            labels = Arrays.asList("Week 1", "Week 2", "Week 3", "Week 4");
            vehiclesDueData = Arrays.asList(8, 10, 12, 15);
            completedServicesData = Arrays.asList(5, 8, 7, 10);
        } else if ("quarter".equals(period)) {
            labels = Arrays.asList("Jan", "Feb", "Mar");
            vehiclesDueData = Arrays.asList(30, 35, 40);
            completedServicesData = Arrays.asList(25, 30, 35);
        } else { // year
            labels = Arrays.asList("Q1", "Q2", "Q3", "Q4");
            vehiclesDueData = Arrays.asList(100, 120, 110, 130);
            completedServicesData = Arrays.asList(90, 105, 95, 115);
        }
        
        result.put("labels", labels);
        result.put("vehiclesDue", vehiclesDueData);
        result.put("completedServices", completedServicesData);
        
        return result;
    }
    
    public Map<String, Object> getServiceDistribution(String period) {
        Map<String, Object> result = new HashMap<>();
        List<String> labels = Arrays.asList("Pending", "In Progress", "Completed");
        List<Integer> data;
        
        if ("month".equals(period)) {
            data = Arrays.asList(12, 8, 5);
        } else { // year
            data = Arrays.asList(150, 95, 75);
        }
        
        result.put("labels", labels);
        result.put("data", data);
        
        return result;
    }
}
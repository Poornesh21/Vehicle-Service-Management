// Restapi/src/main/java/com/albany/restapi/repository/VehicleRepository.java
package com.albany.restapi.repository;

import com.albany.restapi.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    
    // Find vehicles by category
    List<Vehicle> findByCategory(String category);
    
    // Find vehicles by customer ID
    List<Vehicle> findByCustomerId(Integer customerId);
    
    // Count vehicles by category
    long countByCategory(String category);
    
    // Custom query to get vehicles due for service
    @Query("SELECT v FROM Vehicle v JOIN ServiceRequest sr ON v.vehicleId = sr.vehicleId WHERE sr.status = 'Received'")
    List<Vehicle> findVehiclesDueForService();
}
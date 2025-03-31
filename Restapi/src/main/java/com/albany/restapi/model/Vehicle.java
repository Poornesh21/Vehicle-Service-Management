// Restapi/src/main/java/com/albany/restapi/model/Vehicle.java
package com.albany.restapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Vehicles")
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Integer vehicleId;
    
    @Column(name = "customer_id")
    private Integer customerId;
    
    @Column(name = "registration_number", unique = true)
    private String registrationNumber;
    
    @Enumerated(EnumType.STRING)
    private Category category;
    
    private String brand;
    private String model;
    private Integer year;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private CustomerProfile customer;
    
    public enum Category {
        Bike, Car, Truck
    }
}
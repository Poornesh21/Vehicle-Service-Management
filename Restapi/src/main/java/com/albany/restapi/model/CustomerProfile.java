package com.albany.restapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "CustomerProfiles")
@Data
public class CustomerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String street;
    private String city;
    private String state;
    
    @Column(name = "postal_code")
    private String postalCode;
    
    @Column(name = "total_services")
    private Integer totalServices = 0;
    
    @Column(name = "last_service_date")
    private LocalDate lastServiceDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "membership_status")
    private MembershipStatus membershipStatus;
    
    public enum MembershipStatus {
        Standard, Premium
    }
}
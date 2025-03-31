// MVC/src/main/java/com/albany/mvc/dto/CustomerDTO.java
package com.albany.mvc.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CustomerDTO {
    private Integer customerId;
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private Integer totalServices;
    private LocalDate lastServiceDate;
    private String membershipStatus;
    private Boolean isActive;
}
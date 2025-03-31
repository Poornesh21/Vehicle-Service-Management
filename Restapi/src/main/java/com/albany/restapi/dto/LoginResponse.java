// Restapi/src/main/java/com/albany/restapi/dto/LoginResponse.java
package com.albany.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private Integer userId;
    private String email;
    private String role;
    private String firstName;
    private String lastName;
}
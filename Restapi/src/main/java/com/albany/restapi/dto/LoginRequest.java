// Restapi/src/main/java/com/albany/restapi/dto/LoginRequest.java
package com.albany.restapi.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private Boolean rememberMe;
}
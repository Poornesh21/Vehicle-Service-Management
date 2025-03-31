// MVC/src/main/java/com/albany/mvc/dto/LoginRequest.java
package com.albany.mvc.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
    private Boolean rememberMe;
}
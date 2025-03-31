// MVC/src/main/java/com/albany/mvc/dto/LoginResponse.java
package com.albany.mvc.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private Integer userId;
    private String email;
    private String role;
    private String firstName;
    private String lastName;
}
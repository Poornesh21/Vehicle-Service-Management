// MVC/src/main/java/com/albany/mvc/controller/LoginController.java
package com.albany.mvc.controller;

import com.albany.mvc.dto.LoginRequest;
import com.albany.mvc.dto.LoginResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private final RestTemplate restTemplate;
    private final String loginApiUrl = "http://localhost:8081/api/auth/login";

    public LoginController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {

        if (error != null) {
            model.addAttribute("error", "Invalid credentials");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully");
        }

        return "admin/login";
    }

    @PostMapping("/login")
    public String processLogin(LoginRequest loginRequest,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        try {
            // Set up headers for API request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create request entity with login data
            HttpEntity<LoginRequest> request = new HttpEntity<>(loginRequest, headers);

            // Make API call to authenticate
            ResponseEntity<LoginResponse> response = restTemplate.postForEntity(
                    loginApiUrl,
                    request,
                    LoginResponse.class
            );

            LoginResponse user = response.getBody();

            // On successful login, store user info in session
            if (user != null) {
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("userEmail", user.getEmail());
                session.setAttribute("userRole", user.getRole());
                session.setAttribute("userName", user.getFirstName() + " " + user.getLastName());
                session.setAttribute("isLoggedIn", true);

                // Redirect based on role
                if ("admin".equalsIgnoreCase(user.getRole())) {
                    return "redirect:/admin/dashboard";
                } else {
                    return "redirect:/customer/dashboard";
                }
            }

            // If we get here, something went wrong
            redirectAttributes.addAttribute("error", "true");
            return "redirect:/login";

        } catch (RestClientException e) {
            // Handle login failure
            redirectAttributes.addAttribute("error", "true");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }
}
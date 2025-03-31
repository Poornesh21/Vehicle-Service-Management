// MVC/src/main/java/com/albany/mvc/controller/AdminController.java
package com.albany.mvc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        // Check if user is logged in
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (isLoggedIn == null || !isLoggedIn) {
            return "redirect:/login";
        }

        // Check if user has admin role
        String userRole = (String) session.getAttribute("userRole");
        if (!"admin".equalsIgnoreCase(userRole)) {
            return "redirect:/login";
        }

        // Add user info to model
        model.addAttribute("userName", session.getAttribute("userName"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));

        return "admin/dashboard";
    }
}
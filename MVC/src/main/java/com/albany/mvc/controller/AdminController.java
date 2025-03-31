// MVC/src/main/java/com/albany/mvc/controller/AdminController.java
package com.albany.mvc.controller;

import com.albany.mvc.service.DashboardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final DashboardService dashboardService;

    public AdminController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

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

        // Add dashboard data to model
        try {
            model.addAttribute("dashboardStats", dashboardService.getDashboardStats());
        } catch (Exception e) {
            // If API is not available yet, don't crash the page
            model.addAttribute("apiError", "Could not load dashboard data: " + e.getMessage());
        }

        return "admin/dashboard";
    }

    // API endpoints for dashboard AJAX requests
    @GetMapping("/api/vehicles-due")
    @ResponseBody
    public Object getVehiclesDue() {
        return dashboardService.getVehiclesDue();
    }

    @GetMapping("/api/vehicles-in-service")
    @ResponseBody
    public Object getVehiclesInService() {
        return dashboardService.getVehiclesInService();
    }

    @GetMapping("/api/completed-services")
    @ResponseBody
    public Object getCompletedServices() {
        return dashboardService.getCompletedServices();
    }

    @GetMapping("/api/service-trends")
    @ResponseBody
    public Object getServiceTrends(@RequestParam(defaultValue = "month") String period) {
        return dashboardService.getServiceTrends(period);
    }

    @GetMapping("/api/service-distribution")
    @ResponseBody
    public Object getServiceDistribution(@RequestParam(defaultValue = "month") String period) {
        return dashboardService.getServiceDistribution(period);
    }
}
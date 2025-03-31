// MVC/src/main/java/com/albany/mvc/service/DashboardService.java
package com.albany.mvc.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class DashboardService {

    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8081/api/dashboard";

    public DashboardService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object getDashboardStats() {
        return restTemplate.getForObject(apiUrl + "/stats", Object.class);
    }

    public Object getVehiclesDue() {
        ResponseEntity<Object> response = restTemplate.exchange(
                apiUrl + "/vehicles-due",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Object>() {}
        );
        return response.getBody();
    }

    public Object getVehiclesInService() {
        ResponseEntity<Object> response = restTemplate.exchange(
                apiUrl + "/vehicles-in-service",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Object>() {}
        );
        return response.getBody();
    }

    public Object getCompletedServices() {
        ResponseEntity<Object> response = restTemplate.exchange(
                apiUrl + "/completed-services",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Object>() {}
        );
        return response.getBody();
    }

    public Object getServiceTrends(String period) {
        return restTemplate.getForObject(apiUrl + "/service-trends?period=" + period, Object.class);
    }

    public Object getServiceDistribution(String period) {
        return restTemplate.getForObject(apiUrl + "/service-distribution?period=" + period, Object.class);
    }
}
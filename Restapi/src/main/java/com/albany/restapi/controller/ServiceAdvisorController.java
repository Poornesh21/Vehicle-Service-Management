// Restapi/src/main/java/com/albany/restapi/controller/ServiceAdvisorController.java
package com.albany.restapi.controller;

import com.albany.restapi.dto.ServiceAdvisorDTO;
import com.albany.restapi.service.ServiceAdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-advisors")
public class ServiceAdvisorController {

    private final ServiceAdvisorService serviceAdvisorService;

    @Autowired
    public ServiceAdvisorController(ServiceAdvisorService serviceAdvisorService) {
        this.serviceAdvisorService = serviceAdvisorService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceAdvisorDTO>> getAllServiceAdvisors() {
        return ResponseEntity.ok(serviceAdvisorService.getAllServiceAdvisors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceAdvisorDTO> getServiceAdvisorById(@PathVariable("id") Integer advisorId) {
        return ResponseEntity.ok(serviceAdvisorService.getServiceAdvisorById(advisorId));
    }

    @PostMapping
    public ResponseEntity<ServiceAdvisorDTO> createServiceAdvisor(@RequestBody ServiceAdvisorDTO advisorDTO) {
        return ResponseEntity.ok(serviceAdvisorService.createServiceAdvisor(advisorDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceAdvisorDTO> updateServiceAdvisor(
            @PathVariable("id") Integer advisorId,
            @RequestBody ServiceAdvisorDTO advisorDTO) {
        return ResponseEntity.ok(serviceAdvisorService.updateServiceAdvisor(advisorId, advisorDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceAdvisor(@PathVariable("id") Integer advisorId) {
        serviceAdvisorService.deleteServiceAdvisor(advisorId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ServiceAdvisorDTO>> searchServiceAdvisors(@RequestParam("q") String searchTerm) {
        return ResponseEntity.ok(serviceAdvisorService.searchServiceAdvisors(searchTerm));
    }
}
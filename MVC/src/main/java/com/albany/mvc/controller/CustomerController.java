// MVC/src/main/java/com/albany/mvc/controller/CustomerController.java
package com.albany.mvc.controller;

import com.albany.mvc.dto.CustomerDTO;
import com.albany.mvc.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "admin/customers";
    }
}

@RestController
@RequestMapping("/api/customers")
class CustomerApiController {

    private final CustomerService customerService;

    public CustomerApiController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable("id") Integer customerId) {
        return customerService.getCustomerById(customerId);
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable("id") Integer customerId, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerId, customerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") Integer customerId) {
        customerService.deleteCustomer(customerId);
    }
}
// MVC/src/main/java/com/albany/mvc/service/CustomerService.java
package com.albany.mvc.service;

import com.albany.mvc.dto.CustomerDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerService {

    private final RestTemplate restTemplate;
    private final String apiUrl = "http://localhost:8081/api/customers";

    public CustomerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CustomerDTO> getAllCustomers() {
        try {
            ResponseEntity<List<CustomerDTO>> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CustomerDTO>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public CustomerDTO getCustomerById(Integer customerId) {
        try {
            return restTemplate.getForObject(apiUrl + "/" + customerId, CustomerDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CustomerDTO> request = new HttpEntity<>(customerDTO, headers);

        return restTemplate.postForObject(apiUrl, request, CustomerDTO.class);
    }

    public CustomerDTO updateCustomer(Integer customerId, CustomerDTO customerDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CustomerDTO> request = new HttpEntity<>(customerDTO, headers);

        ResponseEntity<CustomerDTO> response = restTemplate.exchange(
                apiUrl + "/" + customerId,
                HttpMethod.PUT,
                request,
                CustomerDTO.class
        );
        return response.getBody();
    }

    public void deleteCustomer(Integer customerId) {
        restTemplate.delete(apiUrl + "/" + customerId);
    }
}
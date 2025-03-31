package com.albany.restapi.service;

import com.albany.restapi.dto.CustomerDTO;
import com.albany.restapi.model.CustomerProfile;
import com.albany.restapi.model.User;
import com.albany.restapi.repository.CustomerProfileRepository;
import com.albany.restapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerProfileRepository customerProfileRepository;
    private final UserRepository userRepository;

    public CustomerService(CustomerProfileRepository customerProfileRepository, UserRepository userRepository) {
        this.customerProfileRepository = customerProfileRepository;
        this.userRepository = userRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        return userRepository.findByRole(User.Role.customer).stream()
                .map(this::convertToCustomerDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Integer customerId) {
        CustomerProfile customerProfile = customerProfileRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
        return convertToCustomerDTO(customerProfile.getUser());
    }

    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        User user = new User();
        user.setRole(User.Role.customer);
        user.setEmail(customerDTO.getEmail());
        // Using a default password for now
        user.setPassword("$2a$10$i5H8dK2LUWcW6qoRiM91YeN/wnA9HvXYP8u9LvekQxBw9Fjat8qfG"); // "password" encoded
        user.setFirstName(customerDTO.getFirstName());
        user.setLastName(customerDTO.getLastName());
        user.setPhoneNumber(customerDTO.getPhoneNumber());
        user.setIsActive(true);
        
        User savedUser = userRepository.save(user);
        
        CustomerProfile customerProfile = new CustomerProfile();
        customerProfile.setUser(savedUser);
        customerProfile.setStreet(customerDTO.getStreet());
        customerProfile.setCity(customerDTO.getCity());
        customerProfile.setState(customerDTO.getState());
        customerProfile.setPostalCode(customerDTO.getPostalCode());
        customerProfile.setTotalServices(0);
        customerProfile.setMembershipStatus(CustomerProfile.MembershipStatus.valueOf(
                customerDTO.getMembershipStatus() != null ? customerDTO.getMembershipStatus() : "Standard"));
        
        CustomerProfile savedProfile = customerProfileRepository.save(customerProfile);
        
        customerDTO.setCustomerId(savedProfile.getCustomerId());
        customerDTO.setUserId(savedUser.getUserId());
        return customerDTO;
    }

    @Transactional
    public CustomerDTO updateCustomer(Integer customerId, CustomerDTO customerDTO) {
        CustomerProfile customerProfile = customerProfileRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
        
        User user = customerProfile.getUser();
        user.setFirstName(customerDTO.getFirstName());
        user.setLastName(customerDTO.getLastName());
        user.setEmail(customerDTO.getEmail());
        user.setPhoneNumber(customerDTO.getPhoneNumber());
        user.setIsActive(customerDTO.getIsActive() != null ? customerDTO.getIsActive() : true);
        
        customerProfile.setStreet(customerDTO.getStreet());
        customerProfile.setCity(customerDTO.getCity());
        customerProfile.setState(customerDTO.getState());
        customerProfile.setPostalCode(customerDTO.getPostalCode());
        if (customerDTO.getMembershipStatus() != null) {
            customerProfile.setMembershipStatus(CustomerProfile.MembershipStatus.valueOf(customerDTO.getMembershipStatus()));
        }
        
        userRepository.save(user);
        CustomerProfile updatedProfile = customerProfileRepository.save(customerProfile);
        
        return convertToCustomerDTO(updatedProfile.getUser());
    }

    @Transactional
    public void deleteCustomer(Integer customerId) {
        CustomerProfile customerProfile = customerProfileRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
        
        User user = customerProfile.getUser();
        customerProfileRepository.delete(customerProfile);
        userRepository.delete(user);
    }

    private CustomerDTO convertToCustomerDTO(User user) {
        CustomerProfile profile = customerProfileRepository.findByUserUserId(user.getUserId())
                .orElse(new CustomerProfile());
        
        CustomerDTO dto = new CustomerDTO();
        dto.setUserId(user.getUserId());
        dto.setCustomerId(profile.getCustomerId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setStreet(profile.getStreet());
        dto.setCity(profile.getCity());
        dto.setState(profile.getState());
        dto.setPostalCode(profile.getPostalCode());
        dto.setTotalServices(profile.getTotalServices());
        dto.setLastServiceDate(profile.getLastServiceDate());
        dto.setMembershipStatus(profile.getMembershipStatus() != null ? profile.getMembershipStatus().name() : "Standard");
        dto.setIsActive(user.getIsActive());
        
        return dto;
    }
}
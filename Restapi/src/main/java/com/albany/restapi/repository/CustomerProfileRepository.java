// Restapi/src/main/java/com/albany/restapi/repository/CustomerProfileRepository.java
package com.albany.restapi.repository;

import com.albany.restapi.model.CustomerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Integer> {
    Optional<CustomerProfile> findByUserUserId(Integer userId);
}
// Restapi/src/main/java/com/albany/restapi/repository/UserRepository.java
package com.albany.restapi.repository;

import com.albany.restapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findByRole(User.Role role);
}
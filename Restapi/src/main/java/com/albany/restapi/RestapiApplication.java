// Restapi/src/main/java/com/albany/restapi/RestapiApplication.java
package com.albany.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.albany.restapi.model")  // Explicitly tell Spring to scan this package for entities
@EnableJpaRepositories("com.albany.restapi.repository")  // Explicitly set repository scan
public class RestapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestapiApplication.class, args);
    }
}
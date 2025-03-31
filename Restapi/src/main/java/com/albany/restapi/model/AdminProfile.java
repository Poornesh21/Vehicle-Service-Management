// Restapi/src/main/java/com/albany/restapi/model/AdminProfile.java
package com.albany.restapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AdminProfiles")
@Data
public class AdminProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer adminId;
    
    @Column(name = "user_id")
    private Integer userId;
    
    @OneToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
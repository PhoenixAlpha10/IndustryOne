package com.industryone.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applicant_users")
public class ApplicantUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "business_name")
    private String businessName;

    @Column
    private String mobile;

    @Column(nullable = false)
    private boolean active = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public ApplicantUser() {}

    public ApplicantUser(String email, String passwordHash, String fullName, String businessName, String mobile) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.businessName = businessName;
        this.mobile = mobile;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getFullName() { return fullName; }
    public String getBusinessName() { return businessName; }
    public String getMobile() { return mobile; }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public void setActive(boolean active) { this.active = active; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public User toUser() {
        return new User(this.id, this.email, this.passwordHash, "ENTREPRENEUR", this.fullName, this.active);
    }
}

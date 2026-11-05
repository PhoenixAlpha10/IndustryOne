package com.industryone.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "officer_users")
public class OfficerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String department = "General Scrutiny Directorate";

    @Column(name = "desk_id")
    private String deskId;

    @Column(nullable = false)
    private boolean active = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public OfficerUser() {}

    public OfficerUser(String email, String passwordHash, String fullName, String department, String deskId) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        if (department != null && !department.isBlank()) {
            this.department = department;
        }
        this.deskId = deskId;
        this.active = true;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getFullName() { return fullName; }
    public String getDepartment() { return department; }
    public String getDeskId() { return deskId; }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setDepartment(String department) { this.department = department; }
    public void setDeskId(String deskId) { this.deskId = deskId; }
    public void setActive(boolean active) { this.active = active; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public User toUser() {
        return new User(this.id, this.email, this.passwordHash, "OFFICER", this.fullName, this.active);
    }
}

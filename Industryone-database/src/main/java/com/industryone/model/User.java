package com.industryone.model;

public class User {

    private Long id;
    private String email;
    private String passwordHash;
    private String role;
    private String fullName;
    private boolean active = true;

    public User() {}

    public User(Long id, String email, String passwordHash, String role, String fullName, boolean active) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.fullName = fullName;
        this.active = active;
    }

    public User(String email, String passwordHash, String role, String fullName, boolean active) {
        this(null, email, passwordHash, role, fullName, active);
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }
    public String getFullName() { return fullName; }
    public boolean isActive() { return active; }

    public void setId(Long id) { this.id = id; }
    public void setEmail(String email) { this.email = email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setRole(String role) { this.role = role; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setActive(boolean active) { this.active = active; }
}

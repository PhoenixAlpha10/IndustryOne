package com.industryone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "auth_tokens")
public class AuthToken {

    @Id
    @Column(length = 100)
    private String token;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "role", nullable = false, length = 50)
    private String role;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    public AuthToken() {}

    public AuthToken(String token, Long userId, String role, String email, String fullName, LocalDateTime expiresAt) {
        this.token = token;
        this.userId = userId;
        this.role = role;
        this.email = email;
        this.fullName = fullName;
        this.expiresAt = expiresAt;
    }

    public String getToken() { return token; }
    public Long getUserId() { return userId; }
    public String getRole() { return role; }
    public String getEmail() { return email; }
    public String getFullName() { return fullName; }
    public LocalDateTime getExpiresAt() { return expiresAt; }

    public User getUser() {
        return new User(this.userId, this.email, "", this.role, this.fullName, true);
    }

    public void setToken(String token) { this.token = token; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setRole(String role) { this.role = role; }
    public void setEmail(String email) { this.email = email; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
}

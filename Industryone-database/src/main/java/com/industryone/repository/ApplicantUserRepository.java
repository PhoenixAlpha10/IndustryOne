package com.industryone.repository;

import com.industryone.model.ApplicantUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantUserRepository extends JpaRepository<ApplicantUser, Long> {
    Optional<ApplicantUser> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
}

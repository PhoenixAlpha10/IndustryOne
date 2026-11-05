package com.industryone.repository;

import com.industryone.model.OfficerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficerUserRepository extends JpaRepository<OfficerUser, Long> {
    Optional<OfficerUser> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
}

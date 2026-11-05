package com.industryone.service;

import com.industryone.model.AdminUser;
import com.industryone.model.ApplicantUser;
import com.industryone.model.OfficerUser;
import com.industryone.model.User;
import com.industryone.repository.AdminUserRepository;
import com.industryone.repository.ApplicantUserRepository;
import com.industryone.repository.OfficerUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final AdminUserRepository adminUserRepository;
    private final OfficerUserRepository officerUserRepository;
    private final ApplicantUserRepository applicantUserRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(AdminUserRepository adminUserRepository,
                       OfficerUserRepository officerUserRepository,
                       ApplicantUserRepository applicantUserRepository) {
        this.adminUserRepository = adminUserRepository;
        this.officerUserRepository = officerUserRepository;
        this.applicantUserRepository = applicantUserRepository;
    }

    public Optional<User> authenticate(String email, String password, String role) {
        if (email == null || password == null || role == null) {
            return Optional.empty();
        }

        String normalizedEmail = email.trim().toLowerCase();
        String normalizedRole = role.trim().toUpperCase();

        return switch (normalizedRole) {
            case "ADMIN" -> adminUserRepository.findByEmailIgnoreCase(normalizedEmail)
                    .filter(AdminUser::isActive)
                    .filter(u -> passwordEncoder.matches(password, u.getPasswordHash()))
                    .map(AdminUser::toUser);

            case "OFFICER" -> officerUserRepository.findByEmailIgnoreCase(normalizedEmail)
                    .filter(OfficerUser::isActive)
                    .filter(u -> passwordEncoder.matches(password, u.getPasswordHash()))
                    .map(OfficerUser::toUser);

            case "ENTREPRENEUR", "APPLICANT" -> applicantUserRepository.findByEmailIgnoreCase(normalizedEmail)
                    .filter(ApplicantUser::isActive)
                    .filter(u -> passwordEncoder.matches(password, u.getPasswordHash()))
                    .map(ApplicantUser::toUser);

            default -> Optional.empty();
        };
    }

    public User createUser(String email, String password, String role, String fullName) {
        String normalizedEmail = email.trim().toLowerCase();
        String normalizedRole = role == null || role.isBlank() ? "ENTREPRENEUR" : role.trim().toUpperCase();
        String hash = passwordEncoder.encode(password);
        String name = fullName == null || fullName.isBlank() ? normalizedEmail : fullName.trim();

        return switch (normalizedRole) {
            case "ADMIN" -> adminUserRepository.save(
                    new AdminUser(normalizedEmail, hash, name, "National Directorate")
            ).toUser();

            case "OFFICER" -> officerUserRepository.save(
                    new OfficerUser(normalizedEmail, hash, name, "General Scrutiny Directorate", null)
            ).toUser();

            default -> applicantUserRepository.save(
                    new ApplicantUser(normalizedEmail, hash, name, name, null)
            ).toUser();
        };
    }

    public boolean exists(String email, String role) {
        if (email == null || role == null) return false;
        String normalizedEmail = email.trim().toLowerCase();
        String normalizedRole = role.trim().toUpperCase();

        return switch (normalizedRole) {
            case "ADMIN" -> adminUserRepository.existsByEmailIgnoreCase(normalizedEmail);
            case "OFFICER" -> officerUserRepository.existsByEmailIgnoreCase(normalizedEmail);
            case "ENTREPRENEUR", "APPLICANT" -> applicantUserRepository.existsByEmailIgnoreCase(normalizedEmail);
            default -> false;
        };
    }
}

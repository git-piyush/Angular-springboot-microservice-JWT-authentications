package com.javatechie.repository;

import com.javatechie.entity.PasswordResetDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordResetDetails, Long> {

    Optional<PasswordResetDetails> findByOtp(String otp);
}

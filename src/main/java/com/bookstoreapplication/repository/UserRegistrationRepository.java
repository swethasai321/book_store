package com.bookstoreapplication.repository;

import com.bookstoreapplication.module.UserRegistrationModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRegistrationRepository extends JpaRepository<UserRegistrationModule, Integer> {
    @Query(value = "select * from user_registration_module where userid= :userId", nativeQuery = true)
    Optional<UserRegistrationModule> getUserById( UserRegistrationModule userId);

    Optional<UserRegistrationModule> findByEmailIdAndPassword(String email_Id, String password);
}
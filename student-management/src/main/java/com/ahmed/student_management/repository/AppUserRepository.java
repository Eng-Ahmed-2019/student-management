package com.ahmed.student_management.repository;

import java.util.Optional;
import com.ahmed.student_management.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long>{
    Optional<AppUser> findByUsername(String username);

    boolean existsByUsername(String username);
}
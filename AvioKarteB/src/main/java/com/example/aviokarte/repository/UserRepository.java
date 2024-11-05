package com.example.aviokarte.repository;

import com.example.aviokarte.enums.UserRole;
import com.example.aviokarte.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    User getByUsername(String username);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);

    List<User> findAllByRole(UserRole role);
}

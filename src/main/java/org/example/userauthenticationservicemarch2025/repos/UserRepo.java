package org.example.userauthenticationservicemarch2025.repos;

import org.example.userauthenticationservicemarch2025.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByEmailId(String email);

    Optional<User> findUserById(Long id);
}

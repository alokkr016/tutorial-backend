package io.github.alokkr016.tutorial_backend.repository;

import io.github.alokkr016.tutorial_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}


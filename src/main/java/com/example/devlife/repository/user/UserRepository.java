package com.example.devlife.repository.user;

import com.example.devlife.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByProviderId(String providerId);
    boolean existsByProviderId(String providerId);
    boolean existsByNickname(String nickname);
}

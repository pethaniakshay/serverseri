package com.serverseri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverseri.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUserId(Long userId);
  User findByEmail(String email);
}
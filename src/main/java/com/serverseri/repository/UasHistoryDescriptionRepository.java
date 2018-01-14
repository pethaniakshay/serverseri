package com.serverseri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverseri.model.UserAccountStatusHistoryDescription;

public interface UasHistoryDescriptionRepository extends JpaRepository<UserAccountStatusHistoryDescription, Long> {

  UserAccountStatusHistoryDescription findByDescriptionId(long descriptionId);
}
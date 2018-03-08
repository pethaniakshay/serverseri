package com.serverseri.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverseri.model.UserAccountStatusHistory;

public interface UserAccountStatusHistoryRepository extends JpaRepository<UserAccountStatusHistory, Long>{

  UserAccountStatusRepository findByUasHistoryId(long uasHistoryId);
}

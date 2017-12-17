package com.serverseri.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverseri.model.UserAccountStatus;

public interface UserAccountStatusRepository extends JpaRepository<UserAccountStatus,Long>{
  UserAccountStatus findByUserAccountStatusId(long uasId);

  @Override
  List<UserAccountStatus> findAll();
}

package com.serverseri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverseri.model.MasterServerLog;

public interface MasterServerLogRepository extends JpaRepository<MasterServerLog, Long>{

  MasterServerLog findByLogId(Long id);

}

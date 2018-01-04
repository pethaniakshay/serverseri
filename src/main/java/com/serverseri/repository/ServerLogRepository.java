package com.serverseri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverseri.model.ServerLog;

public interface ServerLogRepository extends JpaRepository<ServerLog,Long>{

}

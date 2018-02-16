package com.serverseri.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serverseri.model.MasterServerLog;
import com.serverseri.model.Server;
import com.serverseri.model.ServerLog;

public interface ServerLogRepository extends JpaRepository<ServerLog,Long>{

  @Override
  List<ServerLog> findAll();

  List<ServerLog> findAllByLogDate(Timestamp date);

  List<ServerLog> findAllByMstServerLog(MasterServerLog masterServerLog);

  List<ServerLog> findAllByMstServerLogLogId(Long masterServerLogId);

  List<ServerLog> findAllByServer(Server server);

  List<ServerLog> findAllByServerServerId(Long serverId);

  ServerLog findByServerLogId(Long serverLogId);
}

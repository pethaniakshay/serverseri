package com.serverseri.dao;

import java.sql.Timestamp;
import java.util.List;

import com.serverseri.model.MasterServerLog;
import com.serverseri.model.Server;
import com.serverseri.model.ServerLog;

public interface ServerLogDao {

  List<ServerLog> findAll();

  List<ServerLog> findAllByLogDate(Timestamp date);

  List<ServerLog> findAllByMstServerLog(MasterServerLog masterServerLog);

  List<ServerLog> findAllByMstServerLogLogId(Long masterServerLogId);

  List<ServerLog> findAllByServer(Server server);

  List<ServerLog> findAllByServerServerId(Long serverId);

  ServerLog findByServerLogId(Long serverLogId);

  ServerLog save(ServerLog serverLog);

}

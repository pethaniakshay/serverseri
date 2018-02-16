package com.serverseri.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serverseri.model.MasterServerLog;
import com.serverseri.model.Server;
import com.serverseri.model.ServerLog;
import com.serverseri.repository.ServerLogRepository;

@Service
public class ServerLogDaoImpl implements ServerLogDao{

  @Autowired
  ServerLogRepository repository;

  @Override
  public List<ServerLog> findAll() {
    return repository.findAll();
  }

  @Override
  public List<ServerLog> findAllByLogDate(Timestamp date) {
    return repository.findAllByLogDate(date);
  }

  @Override
  public List<ServerLog> findAllByMstServerLog(MasterServerLog masterServerLog) {
    return repository.findAllByMstServerLog(masterServerLog);
  }

  @Override
  public List<ServerLog> findAllByMstServerLogLogId(Long masterServerLogId) {
    return repository.findAllByMstServerLogLogId(masterServerLogId);
  }

  @Override
  public List<ServerLog> findAllByServer(Server server) {
    return repository.findAllByServer(server);
  }

  @Override
  public List<ServerLog> findAllByServerServerId(Long serverId) {
    return repository.findAllByServerServerId(serverId);
  }

  @Override
  public ServerLog findByServerLogId(Long serverLogId) {
    return repository.findByServerLogId(serverLogId);
  }

  @Override
  public ServerLog save(ServerLog serverLog) {
    return repository.saveAndFlush(serverLog);
  }
}

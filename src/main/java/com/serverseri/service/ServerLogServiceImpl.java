package com.serverseri.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serverseri.core.utils.DateTimeUtility;
import com.serverseri.dao.MasterServerLogDao;
import com.serverseri.dao.ServerLogDao;
import com.serverseri.model.MasterServerLog;
import com.serverseri.model.Server;
import com.serverseri.model.ServerLog;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServerLogServiceImpl implements ServerLogService{

  @Autowired
  ServerLogDao dao;

  @Autowired
  MasterServerLogDao masterServerLogDao;

  @Override
  public void log(Long serverId, Long masterServerLogId, String description) {
    // TODO Auto-generated method stub
  }

  @Override
  public void log(ServerLog serverLog) {
    // TODO Auto-generated method stub
  }

  @Override
  public void log(Server server, Long masterServerLogId, String description) {
    ServerLog logs = new ServerLog();
    MasterServerLog masterLog = masterServerLogDao.findByLogId(masterServerLogId);
    logs.setServer(server);
    logs.setMstServerLog(masterLog);
    logs.setDescription(description);
    logs.setLogDate(DateTimeUtility.currentUTCTimeStamp());
    dao.save(logs);
    log.info("Server Logged: "+ description);
  }

  @Override
  public void log(Server server, Long masterServerLogId, String description, LocalDateTime time) {
    // TODO Auto-generated method stub

  }

  @Override
  public void log(Long serverId, Long masterServerLogId, String description, LocalDateTime time) {
    // TODO Auto-generated method stub

  }
}

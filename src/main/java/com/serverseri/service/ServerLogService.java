package com.serverseri.service;

import java.time.LocalDateTime;

import com.serverseri.model.Server;
import com.serverseri.model.ServerLog;

public interface ServerLogService {

  void log(Long serverId, Long masterServerLogId, String description);

  void log(ServerLog serverLog);

  void log(Server server, Long masterServerLogId, String description);

  void log(Server server, Long masterServerLogId, String description, LocalDateTime time);

  void log(Long serverId, Long masterServerLogId, String description, LocalDateTime time);
}

package com.serverseri.service;

import com.serverseri.model.Server;

public interface SshService {

  boolean checkConnection(Server server);

  int executeCommand(Server server, String command);

}

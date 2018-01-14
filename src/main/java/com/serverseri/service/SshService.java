package com.serverseri.service;

import com.serverseri.model.Server;

public interface SshService {

  int executeCommand(Server server, String command);

  boolean checkConnection(Server server);

}

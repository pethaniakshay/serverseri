package com.serverseri.service;

import java.util.Map;

import com.serverseri.model.Server;

public interface ServerService {

  Map<String,Object> addNewServer(Server server);

}

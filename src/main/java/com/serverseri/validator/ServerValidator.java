package com.serverseri.validator;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.serverseri.core.constants.Constants;
import com.serverseri.model.Server;
import com.serverseri.repository.ServerRepository;

@Component
public class ServerValidator {

  @Autowired
  IPAddressValidator ipAddressValidator;

  @Autowired
  ServerRepository serverRepo;

  public Map<String,Object> validateNewServer(Map<String,Object> serverData){

    Map<String,Object> response = Maps.newHashMap();

    String hostName = (String)serverData.get("hostName");
    String userName = (String)serverData.get("userName");
    String password = (String)serverData.get("password");
    String serverName = (String)serverData.get("serverName");

    if(Strings.isNullOrEmpty(hostName)) {
      response.put(Constants.STATUS, Constants.STATUS_ERROR);
      response.put(Constants.MESSAGE,"Please enter the host name");
      return response;
    }

    hostName = hostName.trim();

    if(serverRepo.findServerByHostName(hostName)!= null) {
      response.put(Constants.STATUS, Constants.STATUS_ERROR);
      response.put(Constants.MESSAGE,"Server Exist in the database.");
      return response;
    }

    //TODO Future Implementation Idea
    //TODO Check weather the server is blocked or suspended previously by violating the policies.
    //TODO Check weather user have reached the maximum number of registration limit


    //TODO perform password lenght validation  and username lenght validation. Server name length

    if(Strings.isNullOrEmpty(userName)) {
      //Please Enter the user name
      response.put(Constants.STATUS, Constants.STATUS_ERROR);
      response.put(Constants.MESSAGE,"Please enter the user name");
      return response;
    }

    if(Strings.isNullOrEmpty(password)) {
      //Please Enter the password.
      response.put(Constants.STATUS, Constants.STATUS_ERROR);
      response.put(Constants.MESSAGE,"Please enter the password");
      return response;
    }

    //TODO implement password strength utility and also check the Linux minimum password strength requirement.

    if(Strings.isNullOrEmpty(serverName)) {
      serverName = userName + "@" + hostName;
    }
    serverName = serverName.trim();

    Server server = new Server();
    server.setHostName(hostName);
    server.setUserName(userName);
    server.setPassword(password);
    server.setServerName(serverName);

    response.put(Constants.STATUS, Constants.STATUS_SUCCESS);
    response.put("server",server);

    return response;
  }
}

package com.serverseri.service;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serverseri.core.constants.Constants;
import com.serverseri.model.Server;
import com.serverseri.model.User;
import com.serverseri.repository.ServerRepository;
import com.serverseri.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ServerServiceImpl implements ServerService{

  @Autowired
  ServerRepository serverRepo;

  @Autowired
  SshService sshService;

  @Autowired
  SecurityService securityService;

  @Autowired
  UserRepository userRepo;

  @Override
  public Map<String,Object> addNewServer(Map<String,Object> serverData){

    Map<String ,Object> response = new HashMap<>();
    Server server = new Server();

    try{
      //Getting the values in the object
      log.debug("In Service: "+ serverData.toString());
      server.setHostName((String)serverData.get("hostName"));
      server.setUserName((String)serverData.get("userName"));
      server.setPassword((String)serverData.get("password"));
      server.setServerName((String)serverData.get("serverName"));
      //making connection to verify the credential

      log.debug("Host Name: "+ server.getHostName());
      if(!sshService.checkConnection(server)){
        log.debug("Server can not be connected");
        response.put(Constants.STATUS, Constants.STATUS_ERROR);
        response.put(Constants.MESSAGE,"Your Server Credentials Are incorrect or Host is unreachable!");
        return response;
      }
      log.info("Server connected successfully");


      //saving the object

      String email = securityService.findLoggedInUsername();

      User user = userRepo.findByEmail(email);
      server.setUser(user);
      server.setCreatedDate(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())));
      serverRepo.saveAndFlush(server);

      log.info("Server registered");
      response.put(Constants.STATUS, Constants.STATUS_SUCCESS);
      response.put(Constants.MESSAGE,"Server registered successfully");

    } catch(Exception e){
      response.put(Constants.STATUS, Constants.STATUS_ERROR);
      response.put(Constants.MESSAGE,"Error while registering the server");
      log.error("Error: ",e);
      return response;
    }
    return response;
  }

}

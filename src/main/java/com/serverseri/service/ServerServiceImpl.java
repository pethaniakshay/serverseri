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
  SecurityService securityService;

  @Autowired
  UserRepository userRepo;

  @Override
  public Map<String,Object> addNewServer(Server server){

    Map<String ,Object> response = new HashMap<>();
    try{
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
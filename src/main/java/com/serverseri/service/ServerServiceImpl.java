package com.serverseri.service;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serverseri.core.constants.Constants;
import com.serverseri.dao.repository.ServerRepository;
import com.serverseri.dao.repository.UserRepository;
import com.serverseri.model.Server;
import com.serverseri.model.User;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class ServerServiceImpl implements ServerService{

  @Autowired
  ServerRepository serverRepo;

  @Autowired
  SecurityService securityService;

  @Autowired
  ServerLogService serverLogService;

  @Autowired
  UserRepository userRepo;

  @SuppressWarnings("boxing")
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
      serverLogService.log(server, Constants.SERVER_CREATE,"Server Created");

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
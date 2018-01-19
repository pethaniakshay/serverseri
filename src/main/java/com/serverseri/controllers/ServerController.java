package com.serverseri.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.serverseri.core.constants.Constants;
import com.serverseri.model.Server;
import com.serverseri.service.ServerService;
import com.serverseri.service.SshService;
import com.serverseri.validator.ServerValidator;

import lombok.extern.slf4j.Slf4j;

@Controller(value="/server")
@Slf4j
public class ServerController {
  
  @Autowired
  ServerValidator serverValidator;
  
  @Autowired
  SshService sshService;
  
  @Autowired
  ServerService serverService;
  
  @RequestMapping("/ajax_save")
  public Map<String,Object> addNewServer(@RequestParam Map<String ,Object> newServerForm) {
    
    Map<String,Object> response = serverValidator.validateNewServer(newServerForm); 
    if(response.get(Constants.STATUS).equals(Constants.STATUS_SUCCESS)) {
      //GO to save the server
      Server server = (Server)response.get("server");
      log.debug("Host Name: "+ server.getHostName());
      if(!sshService.checkConnection(server)){
        log.debug("Server can not be connected");
        response.put(Constants.STATUS, Constants.STATUS_ERROR);
        response.put(Constants.MESSAGE,"Your Server Credentials Are incorrect or Host is unreachable!");
        return response;
      } 
      response = serverService.addNewServer(server);
      log.info("Server connected successfully");
      return response;
    }  
    return response;
  }
}

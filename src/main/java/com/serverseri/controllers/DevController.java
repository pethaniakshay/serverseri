package com.serverseri.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serverseri.service.ServerService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/dev")
@Slf4j
public class DevController {

  @Autowired
  ServerService serverService;

  @RequestMapping("/add_new")
  public String addServer(){

    log.debug("new server");
    return "dev_new_server";
  }

  @RequestMapping(value ="/ajax_add_server_form", method = RequestMethod.POST)
  public @ResponseBody Map<String, Object> processServerForm(@RequestParam Map<String ,Object> newServerForm){
    Map<String ,Object> response = new HashMap<>();
    log.debug(newServerForm.toString());
    response.put("message","For submittes successfully");
    return response;
  }
}

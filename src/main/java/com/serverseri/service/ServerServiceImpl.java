package com.serverseri.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serverseri.repository.ServerRepository;

@Service
public class ServerServiceImpl implements ServerService{

  @Autowired
  ServerRepository serverRepo;

  @Override
  public Map<String,Object> addNewServer(Map<String,Object> serverData){

    Map<String ,Object> response = new HashMap<>();

    //validating the credentials





    //making connection to verify the credential



    //saving the object




    return response;

  }

}

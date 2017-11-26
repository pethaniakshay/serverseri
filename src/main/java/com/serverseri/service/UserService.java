package com.serverseri.service;

import java.util.Map;

import com.serverseri.model.User;

public interface UserService {

  void save(User user);
  User findByUsername(String username);
  Map<String,Object> processRegistration();
  User findByEmail(String email);
}

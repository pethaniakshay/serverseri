package com.serverseri.service;

import com.serverseri.model.User;

public interface UserService {

  void save(User user);
  User findByUsername(String username);
}

package com.serverseri.service;

import java.util.Map;

import com.serverseri.dto.StandardResponse;
import com.serverseri.model.User;

public interface UserService {

  void save(User user);
  Map<String,Object> processRegistration(User user);
  User findByEmail(String email);
  Map<String,Object> updateUserStatusToVerified(Long userId);
  StandardResponse sendPasswordVerifiactionLink(String mailId);
}

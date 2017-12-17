package com.serverseri.service;

public interface SecurityService {

  String findLoggedInUsername();

  void autologin(String username, String password);

  String getLoggedInUserRole();

  boolean isUserLoggedIn();
}

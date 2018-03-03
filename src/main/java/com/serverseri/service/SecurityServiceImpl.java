package com.serverseri.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService{
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  public String findLoggedInUsername() {
    try {
      User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return user.getUsername();
    } catch(Exception e) {
      log.error("Ignorable Error");
      return null;
    }
  }

  @Override
  public boolean isUserLoggedIn() {
    try {
      User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if(user != null) {
        return true;
      }
      return false;
    } catch(Exception e) {
      log.error("Ignorable Error");
      return false;
    }
  }

  @Override
  public String getLoggedInUserRole() {
    return  SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().toString();
  }

  @Override
  public void autologin(String email, String password) {
    try {
      UserDetails userDetails = userDetailsService.loadUserByUsername(email);
      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
      authenticationManager.authenticate(usernamePasswordAuthenticationToken);

      if (usernamePasswordAuthenticationToken.isAuthenticated()) {
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        log.info(String.format("Auto login %s successfully!", email));
      }
    }
    catch(Exception e)
    {
      log.error("Auto login falied. Error: ", e);
    }
  }
}
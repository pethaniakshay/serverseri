package com.serverseri.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.serverseri.model.Role;
import com.serverseri.model.User;
import com.serverseri.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
  @Autowired
  private UserRepository userRepository;

  private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      User user = userRepository.findByEmail(username);
      if(user == null) {
        logger.info("Login attempt failed due to invalid userName: " + username);
        throw new UsernameNotFoundException(username);
      }
      Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
      for (Role role : user.getRoles()){
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
      }
      return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
    catch (Exception e) {
      logger.error("User Not Found: ", e);
      throw new UsernameNotFoundException(username);
    }
  }
}